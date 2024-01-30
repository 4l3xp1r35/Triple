import java.util.*;

import static java.lang.Math.abs;

/**
 * Implementação do algoritmo Iterative Deepening A* (IDA*).
 * Este algoritmo combina a busca em profundidade com a busca heurística de A*,
 */
public class IDAStar implements IAlgorithm<IDAStar.State>{

    /**
     * Pilha utilizada para armazenar os estados durante a busca.
     */
    protected Stack<State> stack;

    /**
     * Estado atual considerado pelo algoritmo.
     */

    private State actual;

    /**
     * Layout objetivo que o algoritmo está tentando alcançar.
     */
    private static Ilayout objective;

    /**
     * Valor mededio usado para cálculo heurística em cenários de números pares.
     */
    private static int mid;

    /**
     * Primeiro valor medio de um numero ímpar usado para cálculo heurística.
     */
    private static int midOdd;

    /**
     * Segundo valor medio (dividir o numero duas vezes por dois) de um numero ímpar usado para cálculo heurístico.
     */
    private static int secondMidOdd;

    /**
     * Layout inicial do qual a busca começa.
     */
    private static Ilayout inicial;

    /**
     * Limite atual para a busca iterativa em profundidade.
     */
    private static double threshold;

    /**
     * Lista temporária utilizada para ajudar nos cálculos da heurística.
     */
    private static ArrayList<Integer> tempList;

    /**
     * Profundidade da solução encontrada.
     */
    public static double depth;

    /**
     * Número de nós gerados durante a busca.
     */
    public static int nodeGenerated;

    /**
     * Número de nós expandidos durante a busca.
     */
    public static double nodeExpand;


    /**
     * Representa um estado no espaço de estados do problema de busca.
     * Cada estado tem um layout, um pai, um custo de caminho (g) e uma heurística (h).
     */
    static class State implements IState{

        /**
         * Layout atual.
         */
        private final Ilayout layout; //config = 4 2\n357\n698

        /**
         * Estado pai.
         */
        private final State father; //father

        /**
         * Representa o custo desde a raiz ate ao estado atual
         */
        private final int g; //custo desde a raiz ate ao estado atual

        /**
         * Representa a heuristica do estado atual
         */
        private final double h;

        /**
         * Lista de inteiros contendo os numeros medios do numero
         */
        public ArrayList<Integer> list ;


        /**
         * Cria um novo estado com um layout e um estado pai.
         *
         * @param l O layout para este estado.
         * @param n O estado pai, pode ser null se este for o estado raiz.
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            boolean odd = false;

            if (father != null) {
                g = (int) (father.g + l.getG());
                if (inicial.getNumb()<0) odd=true;
                //h=generateHeuristic(l,odd);
                h=0;
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

        }

        /**
         * Gera a heurística para o estado atual baseado na configuração e se o numero é par ou ímpar.
         *
         * @param l O layout para o qual a heurística deve ser calculada.
         * @param odd Verdadeiro se o número de elementos for ímpar, falso caso contrário.
         * @return O valor heurístico calculado.
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
         * @return Uma string que representa o layout do estado.
         */
        public String toString() {
            return layout.toString();
        }

        /**
         * Retorna o custo do caminho desde o estado inicial até este estado.
         *
         * @return O custo do caminho (g).
         */
        public int getG() {
            return g;
        }

        /**
         * Retorna a soma do custo do caminho e do valor heurístico (f = g + h).
         *
         * @return A função de avaliação (f).
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
     final public List<IDAStar.State> sucessores(IDAStar.State n) {
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
     * Resolve o problema de busca utilizando o algoritmo IDA*, procurando o caminho mais curto do estado inicial ao estado objetivo.
     * O método usa uma abordagem de aprofundamento iterativo, onde o limite de custo é aumentado a cada iteração até que o objetivo seja alcançado.
     * A busca é realizada através de uma pilha.
     *
     * @param s O layout inicial a partir do qual a busca começa.
     * @param goal O layout objetivo que estamos a tentar alcançar.
     * @return Um iterador sobre a sequência de estados do caminho solução, do inicial ao objetivo.
     */
    final public Iterator<IDAStar.State> solve(Ilayout s, Ilayout goal) {
        objective = goal;
        mid=objective.getNumb()/2;
        midOdd =(objective.getNumb()-(1))/2;
        secondMidOdd = midOdd /2;

        tempList = new ArrayList<>();
        tempList.add(midOdd);
        tempList.add(secondMidOdd);
        tempList.add(objective.getNumb()-(1));

        stack = new Stack<>();
        State raiz = new State(s, null);

        stack.add(raiz); //insere o estado inicial na lista dos abertos, s = config, null pq n tem pai
        threshold= raiz.getF();
        List<State> sucs;

        double tempThreshold;
        while(true){

            if(stack.isEmpty()) {
                raiz.list= tempList;
                stack.add(raiz);
                System.out.println("Inicial:" + raiz);
            }

            tempThreshold=Integer.MAX_VALUE;
            while (!stack.empty()){
                actual=stack.pop();
                System.out.println("Pai: " + actual.layout.toString());

                if (actual.getF()<tempThreshold && actual.getF()>threshold) {
                    tempThreshold = actual.getF();
                }

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
                else if (actual.getF()<=threshold ){
                    sucs = sucessores(actual);
                    nodeExpand++;
//                    nodeGenerated+=sucs.size();
                    System.out.println("sucessores:");
                    for (State sucessor : sucs) {
                        if (!stack.contains(sucessor)) {
                            System.out.println("Filho"+sucessor.toString());
                            stack.add(sucessor);
                            nodeGenerated++;
                        }
                    }
//                    stack.addAll(sucs);
                }
            }
            threshold=tempThreshold;

        }

    }

}

