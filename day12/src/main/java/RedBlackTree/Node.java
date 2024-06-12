package RedBlackTree;

public class Node implements RedBlack.TreePrinter.PrintableNode {
    //프라이빗으로 선언 (개방폐쇄)
    // 데이터값
    private Integer data;
    // 오른쪽 자식 노드 값
    private Node right;
    // 왼쪽 자식 노드 값
    private Node left;
    // 색깔
    private String color;
    // 엑스트라 블랙(불린값)
    private boolean extraBlack;  //(점이 찍히면 true)



    // 생성자
    public Node() { // NIL node 생성자
        this.color = "black";
    }

    public Node(Integer data) {
        this.data = data;
        this.right = new Node();
        this.left = new Node();
        this.color = "red";
        this.extraBlack = false;
    }
    //데이터는 함부로 만지면 안됨 => 받아와야해
    // 데이터를 받는 생성자
    //  위의 변수들을 초기화 해준다.


    //게터 세터
    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isExtraBlack() {
        return extraBlack;
    }

    public void setExtraBlack(boolean extraBlack) {
        this.extraBlack = extraBlack;
    }

    public String getText(){
        return ""+data +"["+this.color+"]";
    }
}
