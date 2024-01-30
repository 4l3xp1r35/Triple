import java.util.*;

import static java.lang.Math.abs;

/**
 * Classe que implementa o algoritmo A* (A estrela).
 *A implementação utiliza uma fila de prioridade para manter os estados abertos ordenados pelo custo do caminho g mais a heuristica.
 */
public class AStar implements IAlgorithm<AStar.State>{
    /**
     * Fila de prioridade para estados abertos no algoritmo de busca.
     * Os estados são ordenados pelo custo g mais a heuristica.
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
    private static Ilayout objective;

    /**
     * O layout inicial a partir do qual começa a busca.
     */
    private static Ilayout inicial;

    /**
     * Valor medio do objetivo, usado para cálculos heurísticos.
     */
    private static int mid;

    /**
     * Valor medio de um numero ímpar ao objetivo, usado para cálculos heurísticos em cenários específicos.
     */
    private static int midImpar;

    /**
     * Segundo valor medio (dividir o numero duas vezes por dois) do numero ímpar ao objetivo, usado para cálculos heurísticos em cenários específicos.
     */
    private static int secondMidImpar;

    /**
     * Profundidade da solução encontrada.
     */
    public static double depth=0;

    /**
     * Número de nós gerados durante a busca.
     */
    public static int nodeGenerated = 0;

    /**
     * Número de nós expandidos durante a busca.
     */
    public static double nodeExpand = 0;

    /**
     * Lista temporária usada para auxiliar nos cálculos heurísticos.
     */
    private static ArrayList<Integer> tempList;

    /**
     * Classe interna State representa um estado dentro do espaço de busca do algoritmo A*.
     * Cada estado tem um layout, um pai, um custo de caminho (g) e uma heurística (h).
     */
    static class State implements IState{
        /**
         * Layout atual.
         */
        private final Ilayout layout;

        /**
         * Estado pai.
         */
        private final State father;

        /**
         * Representa o custo desde a raiz ate ao estado atual
         */
        private final int g;

        /**
         * Representa a heuristica do estado atual
         */
        private final int h;

        /**
         * Lista de inteiros contendo os numeros medios do numero
         */
        public ArrayList<Integer> list ;

        /**
         * Constrói um novo estado.
         *
         * @param l O layout do estado atual.
         * @param n O estado pai, pode ser null se for o estado inicial.
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            boolean odd = false;

            if (father != null) {
                g = (int) (father.g + l.getG());
                if (inicial.getNumb()<0) odd=true;
                h=generateHeuristic(l,odd);

            }

            else{
                g = 0;
                h=0;
                inicial=l;
                if (inicial.getNumb()<0) {
                    this.list = new ArrayList<>();
                    this.list.addAll(tempList);
                }

            }
            /*
            Primeira heuristica
            if (father != null){
                g = (int) (father.g + l.getG());
                h = abs(l.getNumb()-AStar.inicial.getNumb());

            }

            else{
                g = 0;
                h=0;
                AStar.inicial=l;
            }*/


        }

        /**
         * Gera a heurística para o estado atual baseado na configuração e se o numero é par ou ímpar.
         *
         * @param l O layout atual para o qual a heurística deve ser calculada.
         * @param odd Um booleano indicando se o número de elementos é ímpar.
         * @return O valor heurístico calculado para o estado.
         */
        public int generateHeuristic(Ilayout l,boolean odd){

            if (odd) {
                this.list=new ArrayList<>();
                this.list.addAll(father.list);

                int temp = abs(l.getNumb()-this.list.get(0));
                if (!this.list.isEmpty()){
                    for (int i = 0; i < this.list.size()-1; i++){
                        int dist = abs(l.getNumb()-this.list.get(i+1));
                        if (temp>dist) temp=dist;
                        if (l.getNumb()==this.list.get(i)) this.list.remove(i);
                    }
                    return temp;
                }else return abs(l.getNumb()-objective.getNumb());

            } else {
                if (l.getNumb() <= mid) {
                    return abs(l.getNumb() - mid);
                } else return abs(l.getNumb() - objective.getNumb());
            }

        }

        /**
         * Retorna uma representação em string do estado atual.
         *
         * @return A representação em string do estado.
         */
        public String toString() {
            return layout.toString();
        }

        /**
         * Retorna o custo desde o estado inicial até o estado atual (g).
         *
         * @return O custo de caminho desde o estado inicial.
         */
        public int getG() {
            return g;
        }

        /**
         * Retorna a soma do custo desde o início até o estado atual e a heurística (f = g + h).
         *
         * @return O custo total estimado para alcançar o objetivo a partir do estado inicial.
         */
        public double getF(){
            return g+h;
        }

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
        public boolean equals(Object o) {
            if (o == null) return false;
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
    final public List<AStar.State> sucessores(AStar.State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children(); //coloco numa lista todos os filhos do estado recebido

        for (Ilayout e : children) { //por cada config na lista dos filhos
            if (n.father == null || !e.equals(n.father.layout)) { //verifico para cada config dos filhos se =
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }


    /**
     * Resolve o problema de busca usando o algoritmo A*, procurando o caminho mais curto do estado inicial ao estado objetivo.
     * Este método inicializa as estruturas necessárias e executa o loop de busca até encontrar a solução ou esgotar todos os caminhos possíveis.
     *
     * @param s    O estado inicial do problema de busca.
     * @param goal O estado objetivo que estamos tentando alcançar.
     * @return Um iterador sobre a sequência de estados desde o estado inicial até o estado objetivo,
     *         que representa a solução encontrada pelo algoritmo A*.
     */
    final public Iterator<AStar.State> solve(Ilayout s, Ilayout goal) {
        objective = goal;
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum((s1.getF()) - (s2.getF())));
        fechados = new HashMap<>();
        mid=objective.getNumb()/2;
        midImpar =(objective.getNumb()-(1))/2;
        secondMidImpar = midImpar /2;
        tempList=new ArrayList<>();
        tempList.add(midImpar);
        tempList.add(secondMidImpar);
        tempList.add(objective.getNumb()-(1));
        abertos.add(new State(s, null)); //insere o estado inicial na lista dos abertos, s = config, null pq n tem pai
        List<State> sucs;


        while(true){
            if(abertos.isEmpty()) {
                System.exit(1);
            }

            actual = abertos.poll(); //guardo no estado atual o estado inicial

            if(actual.layout.isGoal(objective)){
                List<State> solution = new ArrayList<>();
                State temp= actual;
                while(temp.father!=null)
                {
                    solution.add(0,temp);
                    temp=temp.father;
                    depth++;
                }
                solution.add(0,temp);
                return solution.iterator();
            }
            else {
                if (!fechados.containsKey(actual.layout)) {
                    sucs = sucessores(actual);
                    fechados.put(actual.layout, actual);
                    nodeExpand++;
                    nodeGenerated+=sucs.size();
                    for (State sucessor : sucs) {
                        if (!fechados.containsKey(sucessor.layout)) {
                            abertos.add(sucessor);

                        }
                    }

                }
            }


            }

        }

    }

