package linkedlist;

public class Node {
    private Integer data;
    private Node nextNode;

    public Node(Integer input) {
        this.data = input;
        this.nextNode = null;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
