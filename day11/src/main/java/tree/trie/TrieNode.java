package tree.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Character data;
    private Map<Character, TrieNode> childNodes;
    private Boolean isLast;
    private Integer count;

    public TrieNode() {
        this.data = null;
        this.childNodes = new HashMap<>();
        this.isLast = false;
        this.count = 0;
    }

    public TrieNode(Character data) {
        this.data = data;
        this.childNodes = new HashMap<>();
        this.isLast = false;
        this.count = 0;
    }

    public TrieNode(Character data, Boolean isLast) {
        this.data = data;
        this.childNodes = new HashMap<>();
        this.isLast = isLast;
        this.count = 0;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    public Map<Character, TrieNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Map<Character, TrieNode> childNodes) {
        this.childNodes = childNodes;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
