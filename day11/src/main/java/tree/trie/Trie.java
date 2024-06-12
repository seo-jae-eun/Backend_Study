package tree.trie;

import java.util.*;

public class Trie {
    
    // trie를 생성할 때 root 노드 생성
    TrieNode root;
    //  root 노드(데이터 없음, 자식들 저장 가능, 마지막 아님)
    public Trie() {
        this.root = new TrieNode();
    }

    // 단어를 입력받아서 반환하는 건 없는 insert 기능
    //  단어의 길이만큼 반복
    //      만약 현재 노드의 자식 노드들 중에서 현재 글자가 포함되어 있으면
    //          현재 노드의 현재 글자에 해당하는 자식 노드로 넘어간다.
    //      그렇지 않으면
    //          새로운 노드 생성(현재 글자, 자식들 저장 가능, 마지막 아님)
    //          현재 노드의 자식 노드에 현재 글자를 키로 새로운 노드를 값으로 추가
    //          현재 노드를 새로 생성한 노드로 변경
    //  현재 노드를 마지막 노드라고 표시
    public void insert(String word) {
        TrieNode currentNode = root;
        for(int i = 0; i < word.length(); i++) {
            if(currentNode.getChildNodes().containsKey(word.charAt(i))) {
                currentNode = currentNode.getChildNodes().get(word.charAt(i));
            }
            else {
                TrieNode newNode = new TrieNode(word.charAt(i));
                currentNode.getChildNodes().put(word.charAt(i), newNode);
                currentNode = newNode;
            }
        }

        if(!currentNode.getLast()) {
            currentNode.setLast(true);
        }
        currentNode.setCount(currentNode.getCount() + 1);
    }

    // 3단계
//    public Boolean search(String word) {
//        TrieNode currentNode = root;
//        for(int i = 0; i < word.length(); i++) {
//            if(currentNode.getChildNodes().containsKey(word.charAt(i))) {
//                currentNode = currentNode.getChildNodes().get(word.charAt(i));
//            }
//            else {
//                return false;
//            }
//        }
//        if(currentNode.getLast()) {
//            return true;
//        }
//        return false;
//    }

    // 4단계
    public void search(String word) {
        TrieNode currentNode = root;
        for(int i = 0; i < word.length(); i++) {
            if(currentNode.getChildNodes().containsKey(word.charAt(i))) {
                currentNode = currentNode.getChildNodes().get(word.charAt(i));
            }
            else {
                System.out.println("없음");
                return ;
            }
        }
        if(currentNode.getLast()) {
            System.out.println(currentNode.getCount() + "개 있음");
            return ;
        }
        System.out.println("없음");
    }

    // 5단계 -> 완성 x -> 망함
//    public Integer count(String word) {
//        TrieNode node = root;
//        Queue<Character> queue = new LinkedList<>();
//        Map<Character, Boolean> visited = new HashMap<>();
//        for (int i = 0; i < word.length(); i++) {
//            if (word.charAt(i) == '?') {
////                queue.addAll(node.getChildNodes().keySet());
////                System.out.println(queue);
//
//                queue.add(node.getData());
//                System.out.println(node.getData());
//                visited.put(node.getData(), true);
//
//                while(!queue.isEmpty()) {
//                    Character outNode = queue.poll();
//                    for(Character key : node.getChildNodes().keySet()) {
//                        if(!visited.containsKey(key) || !visited.get(key) || visited.get(key) == null) {
//                            queue.add(key);
//                            System.out.println(key);
//                            visited.put(key, true);
//                        }
//                    }
//                }
//            }
//            if (!node.getChildNodes().containsKey(word.charAt(i))) return 0;
//            node = node.getChildNodes().get(word.charAt(i));
//        }
//        return node.getCount();
//    }

    public int count(String word) { // BFS (너비 우선 탐색)
        Queue<TrieNode> nodes = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        nodes.add(root);
        index.add(0);
        int count = 0;

        while (!nodes.isEmpty()) {
            TrieNode currentNode = nodes.poll();
            int currentIndex = index.poll();

            if (currentIndex == word.length()) {
                if (currentNode.getLast()) {
                    count += currentNode.getCount();
                }
                continue;
            }

            char ch = word.charAt(currentIndex);
            if (ch == '?') {
                for (Map.Entry<Character, TrieNode> entry : currentNode.getChildNodes().entrySet()) {
                    nodes.add(entry.getValue());
                    index.add(currentIndex + 1);
                }
            } else {
                TrieNode nextNode = currentNode.getChildNodes().get(ch);
                if (nextNode != null) {
                    nodes.add(nextNode);
                    index.add(currentIndex + 1);
                }
            }
        }

        return count;
    }

    public Integer count2(String word) { // DFS (깊이 우선 탐색)
        Stack<TrieNode> nodeStack = new Stack<>();
        Stack<String> wordStack = new Stack<>();

        nodeStack.push(root);
        wordStack.push(word);
        int count = 0;

        while (!nodeStack.isEmpty()) {
            TrieNode currentNode = nodeStack.pop();
            String currentWord = wordStack.pop();

            if (currentWord.isEmpty()) {
                count += currentNode.getCount();
                continue;
            }

            char currentChar = currentWord.charAt(0);
            String remainingWord = currentWord.substring(1);

            if (currentChar == '?') {
                for (TrieNode child : currentNode.getChildNodes().values()) {
                    nodeStack.push(child);
                    wordStack.push(remainingWord);
                }
            } else {
                TrieNode nextNode = currentNode.getChildNodes().get(currentChar);
                if (nextNode != null) {
                    nodeStack.push(nextNode);
                    wordStack.push(remainingWord);
                }
            }
        }

        return count;
    }

    public void printTrie() {
        printTrie(this.root, "", true);
    }

    private void printTrie(TrieNode currentNode, String prefix, boolean isTail) {
        if (currentNode.getData() != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + currentNode.getData() + (currentNode.getLast() ? " (" + currentNode.getCount() + ")" : ""));
        }

        Map<Character, TrieNode> childNodes = currentNode.getChildNodes();
        int size = childNodes.size();
        int i = 0;

        for (Map.Entry<Character, TrieNode> entry : childNodes.entrySet()) {
            i++;
            printTrie(entry.getValue(), prefix + (isTail ? "    " : "│   "), i == size);
        }
    }
}
