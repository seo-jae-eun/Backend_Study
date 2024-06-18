package dijkstra;

public class Main {
    public static void main(String[] args) {

        Solution solution = new Solution();

        // 배달
        int[][] road = new int[][]{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        System.out.println(solution.solution(5, road, 3));

    }
}
