public class Main {
    public static void main(String[] args){
        Graphs graphs = new Graphs(9, false);

        graphs.insert(0,1,1);
        graphs.insert(1,2,1);
        graphs.insert(1,3,1);
        graphs.insert(2,3,1);

        graphs.insert(4,5,1);
        graphs.insert(5,6,1);

        graphs.insert(7,8,1);

        graphs.dfs(0);
        System.out.println();
        System.out.println(graphs);

        int graphCount = graphs.countGraphs();
        System.out.println("total graphs are : " + graphCount);

        System.out.println("is Graph Connected : " + graphs.isGraphConnected());


    }
}
