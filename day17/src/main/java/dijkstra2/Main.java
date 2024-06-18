package dijkstra2;

public class Main {
    public static void main(String[] args) {

        Solution solution = new Solution();

        // 합승 택시 요금
        int[][] fares = new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(solution.solution(6, 4, 6, 2, fares));
    }
}
