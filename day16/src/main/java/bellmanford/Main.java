package bellmanford;

public class Main {
    public static void main(String[] args) {

        int[][] graph = {{1, 3, 33},{4, 5, -2},{4, 3, -20},{3, 5, 1},{1, 4, 20},{2, 3, 10},{2, 4, 50},{0, 1, 20},{0, 2, 10}};
//        int[][] graph = {{0, 1, 8},{0, 3, 7},{0, 4, 2},{1, 5, 1},{3, 5, 8},{3, 7, 9},{5, 7, 4},{6, 7, 5},{2, 6, 7},{4, 6, 2}};;
//        int[][] graph = {{0, 1, 6},{1, 3, 2},{3, 5, 2},{0, 2, 2},{2, 4, 1},{4, 5, 4},{1, 2, 2},{4, 3, 3},{4, 1, -4}};

        BellmanFord bellmanFord = new BellmanFord();

//        bellmanFord.findPath(graph, 0, 6);
        bellmanFord.findPath(graph, 6, 0, 5);
    }
}
