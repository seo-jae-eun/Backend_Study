import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMain {
    public static void main(String[] args) {
//        List<Integer> list0 = Arrays.asList(1, 2);
//        List<Integer> list1 = Arrays.asList(2);
//        List<Integer> list2 = Arrays.asList(0);
//
//        List<List<Integer>> lists = new ArrayList<>();
//        lists.add(list0);
//        lists.add(list1);
//        lists.add(list2);

//        List<Integer> list0 = Arrays.asList(1, 3, 4);
//        List<Integer> list1 = Arrays.asList(0, 2);
//        List<Integer> list2 = Arrays.asList(1, 3);
//        List<Integer> list3 = Arrays.asList(0, 2, 4);
//        List<Integer> list4 = Arrays.asList(0, 3);
//
//        List<List<Integer>> lists = new ArrayList<>();
//        lists.add(list0);
//        lists.add(list1);
//        lists.add(list2);
//        lists.add(list3);
//        lists.add(list4);
//
//
//        Dfs dfs = new Dfs(lists);
//        dfs.search(lists,3);
//        System.out.println("-------------------------");
//        Bfs bfs = new Bfs(lists);
//        bfs.search(lists,3);

        List<Integer> list0 = Arrays.asList(1, 2, 3);
        List<Integer> list1 = Arrays.asList(0, 4, 5);
        List<Integer> list2 = Arrays.asList(0, 6);
        List<Integer> list3 = Arrays.asList(0, 7, 8);
        List<Integer> list4 = Arrays.asList(1, 9);
        List<Integer> list5 = Arrays.asList(1);
        List<Integer> list6 = Arrays.asList(2, 10, 11);
        List<Integer> list7 = Arrays.asList(3);
        List<Integer> list8 = Arrays.asList(3, 12, 13);
        List<Integer> list9 = Arrays.asList(4);
        List<Integer> list10 = Arrays.asList(6);
        List<Integer> list11 = Arrays.asList(6);
        List<Integer> list12 = Arrays.asList(8);
        List<Integer> list13 = Arrays.asList(8);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list0);
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        lists.add(list5);
        lists.add(list6);
        lists.add(list7);
        lists.add(list8);
        lists.add(list9);
        lists.add(list10);
        lists.add(list11);
        lists.add(list12);
        lists.add(list13);


        Dfs dfs = new Dfs(lists);
        dfs.search(lists,0);
        System.out.println("-------------------------");
        Bfs bfs = new Bfs(lists);
        bfs.search(lists,0);

    }
}
