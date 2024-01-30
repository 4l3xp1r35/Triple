import java.util.Iterator;

/**
 * A classe Algorithm é uma classe genérica que delega a busca de soluções no espaço de estados para um algoritmo específico.
 * Isso permite a flexibilidade de trocar algoritmos de busca sem alterar o código que utiliza esta classe.
 *
 * @param <T> O tipo de estado utilizado no algoritmo de busca. Deve estender a interface IState.
 */
public class Algorithm<T extends IState> {
    /**
     * A instância de IAlgorithm que define o algoritmo de busca a ser utilizado.
     */
    private IAlgorithm<T> algorithm;

    /**
     * Constrói um novo objeto Algorithm com a estratégia de algoritmo de busca fornecida.
     *
     * @param strategy A instância de IAlgorithm que define o algoritmo de busca a ser utilizado.
     */
    public Algorithm(IAlgorithm<T> strategy) {
        this.algorithm = strategy;
    }

    /**
     * Inicia a busca de uma solução do estado inicial para o estado objetivo utilizando o algoritmo de busca fornecido.
     *
     * @param start O layout do estado inicial do problema de busca.
     * @param goal O layout do estado objetivo do problema de busca.
     * @return Um iterador que fornece acesso aos estados do caminho da solução do estado inicial ao estado objetivo.
     */
    public Iterator<T> solve(Ilayout start, Ilayout goal) {
        return algorithm.solve(start, goal);
    }

}
