package dijkstra;

import java.util.Arrays;

class Solution {
    final int INF = Integer.MAX_VALUE;
    boolean[] visited;
    int[] costs;
    int[] path;

    public int[] findPath(int nodeNum, int[][] graph) {
        visited = new boolean[nodeNum];
        costs = new int[nodeNum];
        path = new int[nodeNum];

        // 최단거리 배열을 무한대로 채우기
        Arrays.fill(costs, INF);
        // 부모?지나온정점? 배열을 -1로 채우기
        Arrays.fill(path, -1);

        // 시작 정점의 최단거리를 0으로 초기화
        int startNode = 0;
        costs[startNode] = 0;

        for (int i = 0; i < nodeNum - 1; i++) {
            int minNode = findMinNode(costs, visited);
            if (minNode == -1) break;
            visited[minNode] = true;

            for (int[] edge : graph) {
                // 변경
                int from = edge[0] - 1;
                int to = edge[1] - 1;
                int cost = edge[2];

                if (minNode == from && !visited[to] && costs[from] + cost < costs[to]) {
                    costs[to] = costs[from] + cost;
                    path[to] = from;
                } else if (minNode == to && !visited[from] && costs[to] + cost < costs[from]) {
                    costs[from] = costs[to] + cost;
                    path[from] = to;
                }
            }
        }
        return costs;
    }

    private int findMinNode(int[] costs, boolean[] visited) {
        int minCost = INF;
        int minNode = -1;

        for (int i = 0; i < costs.length; i++) {
            if (!visited[i] && costs[i] < minCost) {
                minCost = costs[i];
                minNode = i;
            }
        }

        return minNode;
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[] hours = findPath(N, road);
        System.out.println(Arrays.toString(hours));
        for(int i : hours) {
            if(i <= K) {
                answer++;
            }
        }

        return answer;
    }


}