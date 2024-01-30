import java.util.*;

public class Main {
    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        final int initial = sc.nextInt();
        final int goal = initial*3;
        Num b = new Num(initial);


        //////////////////////////////////////////////////////////////// BestFirst/////////////////////////////////////////////////////////////////////////
        /*IAlgorithm<BestFirst.State> strategy1 = new BestFirst();
        Algorithm<BestFirst.State> pathFinder1 = new Algorithm<>(strategy1);


        long startTimeMillis = System.currentTimeMillis();
        Iterator<BestFirst.State> it1 = pathFinder1.solve(b,new Num(goal));
        long endTimeMillis = System.currentTimeMillis();
        long executionTimeMillis = endTimeMillis - startTimeMillis;
        long executionTimeSecs = executionTimeMillis/1000;

        if (it1==null) System.out.println("no solution found");
        else {
            while(it1.hasNext()) {
                BestFirst.State i = it1.next();
                //AStar.State i = it.next();
                System.out.println(i);
                System.out.println();
                if (!it1.hasNext()) {
                    System.out.println("\n" + i.getG());


                    System.out.println("+---------------------------------------------------------------------------------+");
                    System.out.println("tempo de execucao : " + executionTimeSecs + " segundos e " + executionTimeMillis+" milisegundos");
                    System.out.println();
                    System.out.println("Nodes generated : " + IDAStar.nodeGenerated);
                    System.out.println();
                    System.out.println("Nodes expanded : " + IDAStar.nodeExpand);
                    System.out.println();
                    System.out.println("Depth : " + IDAStar.depth);
                    System.out.println();
                    double penetrance = IDAStar.depth / IDAStar.nodeExpand;
                    System.out.println("Penetrance : " + penetrance);
                    System.out.println();
                    System.out.println("+---------------------------------------------------------------------------------+");

                }
            }
        }*/
        //////////////////////////////////////////////////////////////// AStar/////////////////////////////////////////////////////////////////////////
     /*   IAlgorithm<AStar.State> strategy = new AStar();
        Algorithm<AStar.State> pathFinder = new Algorithm<>(strategy);

        long startTimeMillis = System.currentTimeMillis();
        Iterator<AStar.State> it = pathFinder.solve(b,new Num(goal));
        long endTimeMillis = System.currentTimeMillis();
        long executionTimeMillis = endTimeMillis - startTimeMillis;
        long executionTimeSecs = executionTimeMillis/1000;

        if (it==null) System.out.println("no solution found");
        else {
            while(it.hasNext()) {
                AStar.State i = it.next();
                System.out.println(i);
                if (!it.hasNext()) {
                    System.out.println("\n" + i.getG());
                    System.out.println("+---------------------------------------------------------------------------------+");
                    System.out.println("tempo de execucao : " + executionTimeSecs + " segundos e " + executionTimeMillis+" milisegundos");
                    System.out.println();
                    System.out.println("Nodes generated : " + AStar.nodeGenerated);
                    System.out.println();
                    System.out.println("Nodes expanded : " + AStar.nodeExpand);
                    System.out.println();
                    System.out.println("Depth : " + AStar.depth);
                    System.out.println();
                    double penetrance = AStar.depth / AStar.nodeExpand;
                    System.out.println("Penetrance : " + penetrance);
                    System.out.println();
                    System.out.println("+---------------------------------------------------------------------------------+");

                }
            }
        }
*/

        //////////////////////////////////////////////////////////////// IDA/////////////////////////////////////////////////////////////////////////
        IAlgorithm<IDAStar.State> strategy2 = new IDAStar();
        Algorithm<IDAStar.State> pathFinder2 = new Algorithm<>(strategy2);

        long startTimeMillis = System.currentTimeMillis();
        Iterator<IDAStar.State> it2 = pathFinder2.solve(b,new Num(goal));

        long endTimeMillis = System.currentTimeMillis();
        long executionTimeMillis = endTimeMillis - startTimeMillis;
        long executionTimeSecs = executionTimeMillis/1000;

        if (it2==null) System.out.println("no solution found");
        else {
            while (it2.hasNext()) {
                IDAStar.State i = it2.next();
                //AStar.State i = it.next();
                System.out.println(i);
                if (!it2.hasNext()) {
                    System.out.println("\n" + i.getG());
                    System.out.println("+---------------------------------------------------------------------------------+");
                    System.out.println("tempo de execucao : " + executionTimeSecs + " segundos e " + executionTimeMillis + " milisegundos");
                    System.out.println();
                    System.out.println("Nodes generated : " + IDAStar.nodeGenerated);
                    System.out.println();
                    System.out.println("Nodes expanded : " + IDAStar.nodeExpand);
                    System.out.println();
                    System.out.println("Depth : " + IDAStar.depth);
                    System.out.println();
                    double penetrance = IDAStar.depth / IDAStar.nodeExpand;
                    System.out.println("Penetrance : " + penetrance);
                    System.out.println();
                    System.out.println("+---------------------------------------------------------------------------------+");
                }
            }
        }


        sc.close();
    }
}
