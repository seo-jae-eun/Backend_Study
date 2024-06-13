package programmers;

import java.util.*;

class Solution {
//    public int solution(int[] numbers, int target) {
//        int answer = 0;
//        int sum = 0;
//
//        // 전달받은 numbers를 너비우선탐색으로 탐색
//        //  다음 갈 수 있는 길은 +, - 가지이기 때문에 2가지의 계산 결과를 모두 큐에 넣는다.
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(sum);
//
//        for(int i = 0; i < numbers.length; i++) {
//            int size = queue.size();
//            for (int j = 0; j < size; j++) { // 큐에 들어있는 만큼 반복 << 이 부분을 제대로 안해줘서..
//                sum = queue.poll();
//                queue.add(sum + numbers[i]);
//                queue.add(sum + (-numbers[i]));
//
//            }
//        }
//        while(!queue.isEmpty()) {
//            if(queue.poll() == target) {
//                answer++;
//            }
//        }
//
//        return answer;
//    }

//    public int solution(int n, int[][] computers) {
//        int answer = 0;
//        boolean[] visited = new boolean[n];
//
//        Stack<Integer> stack = new Stack<>();
//
//        for(int i = 0; i < n; i++) {
//            // DFS
//            if(!visited[i]) {
//                stack.push(i);
//                while(!stack.isEmpty()) {
//                    int output = stack.pop();
//                    if(!visited[output]) {
//                        visited[output] = true;
//                        for(int j = 0; j < computers[output].length; j++) {
//                            if(j != output && computers[output][j] == 1) {
//                                stack.push(j);
//                            }
//                        }
//                    }
//                }
//                answer++;
//            }
//        }
//
//
//
//        return answer;
//    }

//    public int solution(int[][] maps) {
//        int answer = 0;
//        int sum = 0;
//        int[] current;
//
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[]{0, 0});
//
//        for(int i = 0; i < maps[0].length; i++) {
//            int size = queue.size();
//            for (int j = 0; j < size; j++) {
//                current = queue.poll();
//                // 동
//                if(current[1] != maps[i].length - 1 && maps[current[0]][current[1]+1] == 1) {
//                    current
//                    queue.add()
//                }
//                System.out.println(Arrays.toString(queue.poll()));
//                // 서
//                // 남
//                // 북
//
//
//            }
//        }
////        while(!queue.isEmpty()) {
////            if(queue.poll() == target) {
////                answer++;
////            }
////        }
//
//        return answer;
//    }

}