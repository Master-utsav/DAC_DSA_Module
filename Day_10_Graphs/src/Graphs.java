import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs {

     public record GraphNode(int node, int weight){};
     private final List<List<GraphNode>> adjacencyMat;
     private int V = 0;
     private boolean isDirected = false;


     public Graphs(int V, boolean isDirected){
          this.V = V;
          this.isDirected = isDirected;
          adjacencyMat = new ArrayList<>();
          for(int i = 0; i < V; i++){
               adjacencyMat.add(new ArrayList<>());
          }
     }


     public void insert(int u , int v , int w){
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
          dfsHelper(startVertex, isVisited);
          System.out.println();
     }

     private void dfsHelper(int startVertex, boolean[] isVisited){
          if(isVisited[startVertex]) return;

          isVisited[startVertex] = true;
          System.out.print(startVertex + " ");
          for(GraphNode l : adjacencyMat.get(startVertex)){
               if(!isVisited[l.node]){
                    dfsHelper(l.node, isVisited);
               }
          }
     }

     public int countGraphs(){
          boolean[] isVisited = new boolean[V];
          int count = 0;
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

     // ==========================================
     // NEW ADDITIONS
     // ==========================================

     /**
      * Breadth-First Search (BFS) Traversal starting from a given source.
      */
     public void bfs(int startVertex) {
          boolean[] visited = new boolean[V];
          Queue<Integer> queue = new LinkedList<>();

          visited[startVertex] = true;
          queue.add(startVertex);

          while (!queue.isEmpty()) {
               int curr = queue.poll();
               System.out.print(curr + " ");

               for (GraphNode neighbor : adjacencyMat.get(curr)) {
                    if (!visited[neighbor.node]) {
                         visited[neighbor.node] = true;
                         queue.add(neighbor.node);
                    }
               }
          }
          System.out.println();
     }

     /**
      * Detects cycles in the graph using BFS.
      * Works for both directed (Kahn's Algorithm / In-Degree) and undirected graphs (Parent Tracking).
      */
     public boolean hasCycleBFS() {
          boolean[] visited = new boolean[V];

          if (isDirected) {
               // Kahn's Algorithm (Topological Sort approach)
               int[] inDegree = new int[V];
               for (int u = 0; u < V; u++) {
                    for (GraphNode edge : adjacencyMat.get(u)) {
                         inDegree[edge.node]++;
                    }
               }

               Queue<Integer> queue = new LinkedList<>();
               for (int i = 0; i < V; i++) {
                    if (inDegree[i] == 0) {
                         queue.add(i);
                    }
               }

               int processedCount = 0;
               while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    processedCount++;

                    for (GraphNode neighbor : adjacencyMat.get(curr)) {
                         inDegree[neighbor.node]--;
                         if (inDegree[neighbor.node] == 0) {
                              queue.add(neighbor.node);
                         }
                    }
               }

               // If processed nodes != V, there exists at least one cycle
               return processedCount != V;

          } else {
               // Undirected Graph BFS Cycle Detection using Parent tracking
               for (int i = 0; i < V; i++) {
                    if (!visited[i]) {
                         if (bfsCycleUndirected(i, visited)) {
                              return true;
                         }
                    }
               }
               return false;
          }
     }

     private boolean bfsCycleUndirected(int src, boolean[] visited) {
          // Store pair of (current_node, parent_node)
          record NodeParent(int node, int parent) {}

          Queue<NodeParent> queue = new LinkedList<>();
          visited[src] = true;
          queue.add(new NodeParent(src, -1));

          while (!queue.isEmpty()) {
               NodeParent current = queue.poll();
               int u = current.node;
               int parent = current.parent;

               for (GraphNode neighbor : adjacencyMat.get(u)) {
                    int v = neighbor.node;

                    if (!visited[v]) {
                         visited[v] = true;
                         queue.add(new NodeParent(v, u));
                    } else if (v != parent) {
                         // Node visited before and it's not the immediate parent -> Cycle detected
                         return true;
                    }
               }
          }
          return false;
     }

     /**
      * Dijkstra's Algorithm for finding single-source shortest paths.
      * Returns an array containing the shortest distances from src to all other vertices.
      */
     public int[] dijkstra(int src) {
          int[] dist = new int[V];
          Arrays.fill(dist, Integer.MAX_VALUE);
          dist[src] = 0;

          // PriorityQueue ordered by current shortest distance
          PriorityQueue<GraphNode> pq = new PriorityQueue<>(Comparator.comparingInt(GraphNode::weight));
          pq.add(new GraphNode(src, 0));

          while (!pq.isEmpty()) {
               GraphNode current = pq.poll();
               int u = current.node;
               int d = current.weight;

               // If we found a longer path already popped, skip
               if (d > dist[u]) continue;

               for (GraphNode neighbor : adjacencyMat.get(u)) {
                    int v = neighbor.node;
                    int weight = neighbor.weight;

                    if (dist[u] + weight < dist[v]) {
                         dist[v] = dist[u] + weight;
                         pq.add(new GraphNode(v, dist[v]));
                    }
               }
          }

          return dist;
     }
}