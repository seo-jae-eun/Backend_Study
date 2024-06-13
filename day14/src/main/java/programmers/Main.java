package programmers;

public class Main {
    public static void main(String[] args) {

        Solution solution = new Solution();

        // 타겟 넘버
//        System.out.println(solution.solution(new int[]{1, 1, 1, 1, 1}, 3));
//        System.out.println(solution.solution(new int[]{4, 1, 2, 1}, 4));



        // 네트워크
//        System.out.println(solution.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));


        // 게임 맵 최단거리
        System.out.println(solution.solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));

    }
}
