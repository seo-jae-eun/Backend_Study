package bellmanford;

import java.util.Arrays;

public class BellmanFord {

    //bellmanford 알고리즘으로 최단거리 찾는 메소드
    //2차원 배열로 넘어옴(간선정보)
    // int[][] graph = {{0, 1, 8},{0, 3, 7},{0, 4, 2},{1, 5, 1},{3, 5, 8},{3, 7, 9},{5, 7, 4},{6, 7, 5},{2, 6, 7},{4, 6, 2}};
//    public boolean findPath(int[][] graph, int start, int n){
//        // 거리(distance) 배열 초기화(Integer.MAX_VALUE)
//        // 시작점 0
//        // 최단거리 구하기 (n-1번 탐색)
//        // n-1번 반복
//        //      전체 간선 검사
//        //          최단거리 업데이트
//        //              지금거리와 시점+가중치 비교한다.
//
//        int INF = Integer.MAX_VALUE;
////        int[] distance = new int[n];
//        int[] distance = new int[n];
//        for(int i = 0; i < distance.length; i++) {
//            if(i == start) distance[i] = 0;
//            else distance[i] = INF;
//        }
//
//        for(int i = 0; i < n; i++) {
//            for(int[] currentGraph : graph) {
//                if(distance[currentGraph[0]] != INF) {
//                    if(distance[currentGraph[0]] + currentGraph[2] < distance[currentGraph[1]]) {
//                        distance[currentGraph[1]] = distance[currentGraph[0]] + currentGraph[2];
//                    }
//                }
//            }
//
//            if(i == n - 1) {
//                for(int[] currentGraph : graph) {
//                    if(distance[currentGraph[0]] != INF) {
//                        if(distance[currentGraph[0]] + currentGraph[2] < distance[currentGraph[1]]) {
//                            System.out.println("음수 순환 발생");
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(Arrays.toString(distance));
//        return true;
//
//        //
//        // graph = {{1, 3, 33},{4, 5, -2},{4, 3, -20},{3, 5, 1},{1, 4, 20},{2, 3, 10},{2, 4, 50},{0, 1, 20},{0, 2, 10}}
//        // distance = [0, INF, INF, INF, INF, INF]
//        // 0번 반복일 때 순환 (n-1)
//        //      전체 간선 검사(0~9)
//        //       {1, 3, 33}      [0, INF, INF, INF, INF, INF]
//        //       {4, 5, -2}      [0, INF, INF, INF, INF, INF]
//        //       {4, 3, -20}     [0, INF, INF, INF, INF, INF]
//        //       {3, 5, 1}       [0, INF, INF, INF, INF, INF]
//        //       {1, 4, 20}      [0, INF, INF, INF, INF, INF]
//        //       {2, 3, 10}      [0, INF, INF, INF, INF, INF]
//        //       {2, 4, 50}      [0, INF, INF, INF, INF, INF]
//        //       {0, 1, 20}      [0, 20, INF, INF, INF, INF]
//        //       {0, 2, 10}      [0, 20, 10, INF, INF, INF]
//
//        // 1번 순환
//        //       {1, 3, 33}      [0, 20, 10, 53, INF, INF]
//        //       {4, 5, -2}      [0, 20, 10, 53, INF, INF]
//        //       {4, 3, -20}     [0, 20, 10, 53, INF, INF]
//        //       {3, 5, 1}       [0, 20, 10, 53, INF, 54]
//        //       {1, 4, 20}      [0, 20, 10, 53, 40, 54]
//        //       {2, 3, 10}      [0, 20, 10, 20, 40, 54]
//        //       {2, 4, 50}      [0, 20, 10, 20, 40, 54]
//        //       {0, 1, 20}      [0, 20, 10, 20, 40, 54]
//        //       {0, 2, 10}      [0, 20, 10, 20, 40, 54]
//    }

    public boolean findPath(int[][] graph, int n, int start, int end){
        // 거리(distance) 배열 초기화(Integer.MAX_VALUE)
        // 시작점 0
        // 최단거리 구하기 (n-1번 탐색)
        // n-1번 반복
        //      전체 간선 검사
        //          최단거리 업데이트
        //              지금거리와 시점+가중치 비교한다.

        int INF = Integer.MAX_VALUE;
//        int[] distance = new int[n];
        int[][] distance = new int[n][2];
        for(int i = 0; i < distance.length; i++) {
            if(i == start) {
                distance[i][0] = 0;
                distance[i][1] = -1;
            }
            else {
                distance[i][0] = INF;
                distance[i][1] = -1;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int[] currentGraph : graph) {
                if(distance[currentGraph[0]][0] != INF) {
                    if(distance[currentGraph[0]][0] + currentGraph[2] < distance[currentGraph[1]][0]) {
                        distance[currentGraph[1]][0] = distance[currentGraph[0]][0] + currentGraph[2];
                        distance[currentGraph[1]][1] = currentGraph[0];
                    }
                }
            }

            if(i == n - 1) {
                for(int[] currentGraph : graph) {
                    if(distance[currentGraph[0]][0] != INF) {
                        if(distance[currentGraph[0]][0] + currentGraph[2] < distance[currentGraph[1]][0]) {
                            System.out.println("음수 순환 발생");
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(distance));

        System.out.print(end);
        while (distance[end][1] != -1) {
            end = distance[end][1];
            System.out.print(" <- "  + end);
        }
        return true;
    }


}
