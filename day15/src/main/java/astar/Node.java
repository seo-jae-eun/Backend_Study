package astar;

public class Node {
    // FGH
    // XY좌표
    // 부모노드

    private Integer x;
    private Integer y;
    private Integer f;
    private Integer g;
    private Integer h;

    private Node parents;

    public Node(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.f=0;
        this.g=0;
        this.h=0;
    }

    public Node(Integer x, Integer y, Node parents) {
        this.x = x;
        this.y = y;
        this.parents = parents;
    }

    public Node(Integer x, Integer y, Integer f, Integer g, Integer h, Node parents) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.g = g;
        this.h = h;
        this.parents = parents;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Node getParents() {
        return parents;
    }

    public void setParents(Node parents) {
        this.parents = parents;
    }
}
