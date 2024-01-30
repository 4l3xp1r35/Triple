
import java.util.*;
/**
 * Classe que implementa o algoritmo de busca Best-First.
 * Este algoritmo seleciona o próximo estado a ser expandido com base na menor estimativa de custo até o objetivo.
 * A implementação utiliza uma fila de prioridade para manter os estados abertos ordenados pelo custo de caminho g.
 */
public class BestFirst implements IAlgorithm<BestFirst.State>{

    /**
     * Fila de prioridade para estados abertos no algoritmo de busca.
     * Os estados são ordenados pelo custo g.
     */
    protected Queue<State> abertos;

    /**
     * Mapa que mantém os estados já fechados durante a busca evitando a reexploração de estados.
     */
    private Map<Ilayout, State> fechados;

    /**
     * O estado atual a ser explorado pelo algoritmo.
     */
    private State actual;

    /**
     * O layout objetivo que o algoritmo está tentando alcançar.
     */
    private Ilayout objective;

    /**
     * Classe interna State representa um estado dentro do espaço de busca do algoritmo Best-First Search.
     * Cada estado tem um layout, um pai, um custo de caminho (g) e uma heurística (h).
     */
    static class State implements IState{

        /**
         * Layout atual.
         */
        private Ilayout layout;

        /**
         * Estado pai.
         */
        private State father;

        /**
         * Representa o custo desde a raiz ate ao estado atual
         */
        private int g;

        /**
         * Construtor para o estado.
         *
         * @param l O layout atual do estado.
         * @param n O estado pai deste estado.
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father!=null)
                g = (int) (father.g + l.getG());
            else g = 0;
        }

        /**
         * Retorna uma representação em string do estado atual.
         *
         * @return Uma string que representa o layout do estado.
         */
        public String toString() { return layout.toString(); }

        /**
         * Retorna o custo do caminho desde o estado inicial até este estado.
         *
         * @return O custo do caminho (g).
         */
        public int getG() {return g;}
        public double getF() {return g;}

        /**
         * Gera um código hash para o estado atual baseado no seu layout.
         *
         * @return O código hash do estado.
         */
        public int hashCode() {
            return toString().hashCode();
        }

        /**
         * Compara o estado atual com outro objeto para verificar a igualdade.
         *
         * @param o O objeto com o qual comparar.
         * @return Verdadeiro se os objetos são iguais, falso caso contrário.
         */
        public boolean equals (Object o) {
            if (o==null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;

            return this.layout.equals(n.layout);

        }
    }

    /**
     * Retorna uma lista de sucessores para um dado estado.
     * Os sucessores são todos os possíveis estados seguintes que podem ser alcançados a partir do estado atual.
     *
     * @param n O estado para o qual encontrar os sucessores.
     * @return Uma lista de estados sucessores.
     */
    final public List<BestFirst.State> sucessores(BestFirst.State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e: children) {
            if (n.father == null || !e.equals(n.father.layout)){
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    /**
     * Soluciona o problema de busca utilizando o algoritmo Best-First Search.
     * Procura o caminho mais curto do estado inicial ao estado objetivo usando a métrica de custo g.
     *
     * @param s O estado inicial da busca.
     * @param goal O estado objetivo que estamos a tentar alcançar.
     * @return Um iterador sobre a sequência de estados desde o estado inicial até o estado objetivo.
     */
    final public Iterator<BestFirst.State> solve(Ilayout s, Ilayout goal) {
        objective = goal;
        /*abertos = new PriorityQueue<State>();*/
        abertos = new PriorityQueue<>(10,

                (s1, s2) -> (int) Math.signum(s2.getG()-s1.getG()));

        /*abertos = new LinkedList<>();*/

        fechados = new HashMap<> ();
        abertos.add(new State(s, null));
        List<State> sucs;
        while (true){
            if(abertos.isEmpty()) System.exit(1);
            actual=abertos.poll();
            System.out.println("Pai: "+actual.layout.toString());
            abertos.remove(actual);
            if(actual.layout.isGoal(objective)){
                List<State> solution = new ArrayList<>();
                State temp= actual;
                while(temp.father!=null)
                {
                    solution.add(0,temp);
                    temp=temp.father;
                }
                solution.add(0,temp);
                return solution.iterator();
            }
            else {
                sucs=sucessores(actual);
                fechados.put(actual.layout, actual);
                System.out.println("sucessores:");
                for(State sucessor: sucs) {

                    System.out.println("Filho"+sucessor.toString());
                    if(!fechados.containsKey(sucessor.layout)){
                        abertos.add(sucessor);
                    }
                }

            }
            System.out.println();
        }

    }
}

