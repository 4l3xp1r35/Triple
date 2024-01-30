import java.util.Iterator;
import java.util.List;

/**
 * Interface que define um algoritmo de busca genérico.
 * Qualquer algoritmo de busca que implementa esta interface deve ser capaz de gerar sucessores
 * de um dado estado e encontrar uma solução do estado inicial ao estado objetivo.
 *
 * @param <T> O tipo parametrizado que estende a interface IState, representando um estado no problema de busca.
 */
public interface IAlgorithm<T extends IState> {
    /**
     * Gera uma lista de sucessores para o estado fornecido.
     *
     * @param n O estado para o qual os sucessores serão gerados.
     * @return Uma lista de estados sucessores.
     */
    List<T> sucessores(T n);

    /**
     * Soluciona o problema de busca, encontrando uma sequência de estados desde o estado inicial até o estado objetivo.
     *
     * @param s O estado inicial da busca.
     * @param goal O estado objetivo que estamos tentando alcançar.
     * @return Um iterador sobre a sequência de estados que compõem o caminho da solução.
     */
    Iterator<T> solve(Ilayout s, Ilayout goal);
}
