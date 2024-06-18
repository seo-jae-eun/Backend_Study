package floydwarshall;

public class Main {
    public static void main(String[] args) {

        Solution solution = new Solution();

        // 순위
        int[][] results = new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution.solution(5, results));
    }
}
