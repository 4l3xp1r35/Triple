import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * A classe Num representa um estado numérico num espaço de um problema.
 * Cada instância de Num possui um número inteiro e um custo associado para alcançar esse estado.
 */
public class Num implements Ilayout,Cloneable {

    /**
     * valor numerico.
     */
    private int number;

    /**
     * valor do custo associado.
     */
    private int g;

    /**
     * Constrói um novo estado Num com um valor numérico.
     * @param i O valor numérico do estado.
     */
    public Num(int i) {
        number=i;
    }

    /**
     * Constrói um novo estado Num com um valor numérico e um custo associado.
     * @param i O valor numérico do estado.
     * @param cost O custo para alcançar este estado.
     */
    public Num(int i, int cost) {
        number=i;
        g=cost;
    }

    /**
     * Verifica se este estado é igual a outro objeto.
     * @param o O objeto a ser comparado com este estado.
     * @return true se o objeto for um estado Num com o mesmo valor numérico; false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if(o==null) return false;

        if(!(o instanceof Num)) return false;

        Num other = (Num) o;

        if (this.number!=other.number) return false;
        return true;
    }

    /**
     * Gera a lista de estados filhos a partir deste estado.
     * @return Uma lista de Ilayout contendo todos os estados filhos possíveis.
     */
    @Override
    public List<Ilayout> children() {
        List<Ilayout> children = new ArrayList<>();

        /*Num child1 = new Num(number + 1,1);
        Num child2 = new Num(number - 1,2);
        Num child3 = new Num(number * 2,3);*/

        Num child1 = new Num(number + 1,1);
        Num child2 = new Num(number - 1,1);
        Num child3 = new Num(number * 2,1);


        /*System.out.println("pai : "+ this.number);
        System.out.println();
        System.out.println("filho 1: "+child1.toString());
        System.out.println("filho 2: "+child2.toString());
        System.out.println("filho 3: "+child3.toString());
        System.out.println();
        System.out.println();*/
        children.add(child1);
        children.add(child2);
        children.add(child3);

        return children;
    }

    /**
     * @return O valor numérico deste estado como uma string.
     */
    @Override
    public String toString() {
        return Integer.toString(number);
    }

    /**
     * Verifica se este estado atinge o objetivo.
     * @param I O layout que representa o estado objetivo.
     * @return true se este estado for o objetivo; false caso contrário.
     */
    @Override
    public boolean isGoal(Ilayout I) {
        return I.equals(this);
    }

    /**
     * Retorna o custo de alcançar este estado a partir de um estado inicial.
     * @return O custo associado a este estado.
     */
    @Override
    public double getG() {
        return g;
    }

    /**
     * Retorna o valor numérico deste estado.
     * @return O número inteiro que representa este estado.
     */
    @Override
    public int getNumb() {
        return number;
    }

    /**
     * @return O código hash deste estado.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
