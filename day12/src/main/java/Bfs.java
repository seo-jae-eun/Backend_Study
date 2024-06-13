import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    Queue<Integer> queue;
    Boolean[] visited;

    public Bfs(List<List<Integer>> lists) {
        this.queue = new LinkedList<>();
        this.visited = new Boolean[lists.size()];
    }

    // 그래프와 시작 노드를 전달받아서 반환하는 건 없는 search 기능
    //  큐에 시작 노드를 push
    //  시작 노드를 방문 처리
    
    //  만약에 큐가 비어있지 않으면 반복
    //      큐에서 노드를 가져온다.
    //      가져온 노드에서 갈 수 있는 노드들을 하나씩 반복
    //          갈 수 있는 노드가 방문한 적이 없으면
    //          큐에 추가하고 방문처리
    public void search(List<List<Integer>> lists, Integer startNode) {
        queue.add(startNode);
        System.out.println(startNode);
        visited[startNode] = true;

        while(!queue.isEmpty()) {
            Integer outNode = queue.poll();
            for(int i = 0; i < lists.get(outNode).size(); i++) {
                if(visited[lists.get(outNode).get(i)] == null || !visited[lists.get(outNode).get(i)]) {
                    queue.add(lists.get(outNode).get(i));
                    System.out.println(lists.get(outNode).get(i));
                    visited[lists.get(outNode).get(i)] = true;
                }
            }
        }
    }

}
