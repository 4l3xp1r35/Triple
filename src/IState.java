/**
 * Interface que define o contrato para objetos de estado num problema de busca.
 * Um estado é uma representação de uma configuração dentro do espaço de busca do problema.
 */
public interface IState {
    /**
     * Fornece uma representação em string do estado atual.
     * @return Uma string que descreve o estado.
     */
    String toString();

    /**
     * Retorna o custo associado ao estado, geralmente o custo para chegar a este estado a partir do estado inicial.
     * @return O custo g do estado.
     */
    int getG();

    /**
     * Retorna geralmente o custo associado (g) mais a heuristica.
     * @return O custo f do estado.
     */
    double getF();

    /**
     * Gera um código hash para o estado, o qual é utilizado para identificar unicamente o estado.
     * @return O código hash do estado.
     */
    int hashCode();

    /**
     * Compara este estado com outro objeto para verificar se são iguais.
     * @param o O objeto com o qual comparar este estado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    boolean equals(Object o);
}
