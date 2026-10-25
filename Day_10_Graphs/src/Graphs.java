import java.util.LinkedList;
import java.util.List;

public class Graphs {

     public record GraphNode(int node, int weight){};
     private final List<List<GraphNode>> adjacencyMat;
     private int V = 0;
     private boolean isDirected = false;


     public Graphs(int V, boolean isDirected){
          this.V = V;
          this.isDirected = isDirected;
          adjacencyMat = new LinkedList<>();
          for(int i = 0; i < V; i++){
               adjacencyMat.add(new LinkedList<>());
          }
     }


     public  void insert(int u , int v , int w){
         GraphNode newNode = new GraphNode(v, w);
         adjacencyMat.get(u).add(newNode);
         if(!isDirected){
              GraphNode newNode2 = new GraphNode(u, w);
              adjacencyMat.get(v).add(newNode2);
         }
     }


     @Override
     public String toString(){
          StringBuilder str = new StringBuilder();
          for(int i = 0; i < V; i++){
               str.append(i).append(" -> ");
               for(GraphNode g : adjacencyMat.get(i)){
                    str.append("(").append(g.node).append(", ").append(g.weight).append("), ");
               }
               str.append("\n");
          }

          return str.toString();
     }


     public void dfs(int startVertex){
          boolean[] isVisited = new boolean[V];
          for(int i = 0; i < V; i++){
               isVisited[i] = false;
          }
          dfsHelper(startVertex, isVisited);
     }
     private void dfsHelper(int startVertex, boolean[] isVisited){
          if(isVisited[startVertex]) return;

          isVisited[startVertex] = true;
          System.out.print(startVertex + ", ");
          for(GraphNode l : adjacencyMat.get(startVertex)){
               if(!isVisited[l.node]){
                    dfsHelper(l.node, isVisited);
               }
          }
     }

     public int countGraphs(){
          boolean[] isVisited = new boolean[V];
          int count = 0;
          for(int i = 0; i < V; i++){
               isVisited[i] = false;
          }
          for(int i = 0; i < V ; i++){
               if(!isVisited[i]){
                    count++;
                    dfsCountHelper(i, isVisited);
               }
          }
          return count;
     }
     public boolean isGraphConnected(){
          boolean[] isVisited = new boolean[V];
          for(int i = 0; i < V; i++){
               isVisited[i] = false;
          }
          dfsCountHelper(0, isVisited);
          for(int i = 0; i < V; i++){
               if(!isVisited[i]) return false;
          }
          return true;
     }
     private void dfsCountHelper(int startVertex, boolean[] isVisited){
          if(isVisited[startVertex]) return;
          isVisited[startVertex] = true;
          for(GraphNode l : adjacencyMat.get(startVertex)){
               if(!isVisited[l.node]){
                    dfsCountHelper(l.node, isVisited);
               }
          }
     }

}
