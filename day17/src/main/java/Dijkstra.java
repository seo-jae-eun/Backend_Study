//import java.util.Arrays;
// // 다익스트라 다시 직접 구현해보기 (덜함;; ㅎㅎ)
//public class Dijkstra {
//
//
//    public void findPath(int n, int[][] graph, int start) { // 정점의 개수, 간선정보(시점,종점,가중치), 시작정점번호
//        final int INF = Integer.MAX_VALUE;
//
//        int[] distance = new int[n];
//        int[] path = new int[n];
//        boolean[] visited = new boolean[n];
//        // 출발 정점은 0으로 나머지는 무한대로 초기화
//        Arrays.fill(distance, INF);
//        Arrays.fill(path, -1);
//        Arrays.fill(visited, false);
//
//        distance[start] = 0;
//        visited[start] = true;
//
//
//
//        // n-1번 반복
//        // 현재 위치한 정점에서 인접한 정점 중 거리가 짧은 정점으로 이동 (방문처리)
//        // 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단거리 테이블 업데이트
//        for(int i = 0; i < n - 1; i++) {
//            // 방문하지 않은 정점 중 최단거리인 정점 찾기
//            int minDistance = INF;
//            int minNode = -1;
//
//            for (int j = 0; j < n; j++) {
//                if (!visited[j] && distance[j] < minDistance) {
//                    minDistance = distance[j];
//                    minNode = j;
//                }
//            }
//
//
////            for(int )
//        }
//    }
//}
