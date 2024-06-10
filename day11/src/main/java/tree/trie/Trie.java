package tree.trie;

import java.util.Map;

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

    // 5단계 -> 완성 x
    public Integer count(String word) {
        TrieNode currentNode = root;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == '?') {
                // 물음표일 때....
            }
            if(currentNode.getChildNodes().containsKey(word.charAt(i))) {
                currentNode = currentNode.getChildNodes().get(word.charAt(i));
            }
            else {
                System.out.println("없음");
                return currentNode.getCount();
            }
        }
        if(currentNode.getLast()) {
            return currentNode.getCount();
        }
        return currentNode.getCount();
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
