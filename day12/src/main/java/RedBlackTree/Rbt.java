package RedBlackTree;

public class Rbt {

    private Node root;

    public Rbt() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // insert() 메서드
    public void insert2(Integer data) {
        //노드 객체를 생성해서 노드에 데이터를 저장
        Node newNode = new Node(data);

        //여기부터는 위치찾기
        // 만약 루트에 데이터를 저장한 적이 없으면
        // 현재노드 객체에 데이터 저장
        //     루트가 현재노드를 가르키도록 설정
        //  그렇지않으면(데이터가 있으면)
        //      1 반복한다(현재 노드의 값이 없을 때까지)
        //         2-1 만약 데이터가 현재 노드의 값보다 작으면
        //            3-1 만약 현재 노드의 왼쪽 노드가 비어있으면
        //              현재노드에 데이터 저장

        //            3-2 그렇지않으면(데이터가 있으면)
        //               현재노드는 현재 노드의 왼쪽 노드를 가르킨다

        //          2-2 그렇지않으면(데이터가 현재 노드의 값보다 크면)
        //              4-1 만약 현재 노드의 오른쪽 노드가 비어있으면
        //                  현재노드에 데이터 저장

        //               4-2 그렇지않으면(데이터가 있으면)
        //                  현재 노드는 현재 노드의 오른쪽 노드를 가리킨다.
    }


    public void insert(Integer data) {
        Node newNode, currNode;
        newNode = new Node(data);
        currNode = root;

        if (root == null){
            newNode.setColor("black");
            root = newNode;
            return;
        } else {
            while (currNode.getData() != null){
                if (currNode.getData() < data){
                    //오른쪽이동
                    if (currNode.getRight().getData() == null){
                        currNode.setRight(newNode);
                        currNode = currNode.getRight();
                        break;
                    } else {
                        currNode = currNode.getRight();
                    }
                } else {
                    //왼쪽이동
                    if (currNode.getLeft().getData() == null){
                        currNode.setLeft(newNode);
                        currNode = currNode.getLeft();
                        break;
                    } else {
                        currNode = currNode.getLeft();
                    }
                }
            }
        }
        //균형 체크

        while (currNode.getColor().equals("red") && getParent(currNode).getColor().equals("red")){
            Node parent = getParent(currNode);
            if (parent == root){
                break;
            }
            Node ancestor = getParent(parent);
            Node uncle = getUncle(ancestor, parent);
            if (uncle.getColor().equals("black")){
                //restructuring
                currNode = reStructuring(currNode, parent, ancestor);
            } else{
                //reColoring
                currNode = reColoring(currNode, parent, ancestor, uncle);
            }
        }
        //균형 체크 메소드에

    }

    public Node getParent(Node newNode) {
        //리턴해줄 부모 노드, 현재 위치 체크할 currNode 선언 및 초기화
        Node parentNode, currNode;
        parentNode = this.root;
        currNode = this.root;

        // 트리의 끝까지 탐색하다가
        // 현재 노드의 L or R 둘중 하나의 데이터가 받아온 노드의 값과 같을 때 멈춤
        while (currNode != null) {
            if (currNode.getRight().getData() == newNode.getData()) {
                parentNode = currNode;
                break;
            } else if (currNode.getLeft().getData() == newNode.getData()) {
                parentNode = currNode;
                break;
            }

            if (currNode.getData() < newNode.getData()) {
                currNode = currNode.getRight();
            } else {
                currNode = currNode.getLeft();
            }
        }
        return parentNode;
    }

    public Node getUncle(Node ancestor, Node parent) {
        if (ancestor.getLeft() == parent) {
            return ancestor.getRight();
        }
        return ancestor.getLeft();
    }

    public Node reStructuring(Node newNode, Node parent, Node ancestor) {
        Node grandAncestor = null;
        if (ancestor!=root){
            grandAncestor = getParent(ancestor);
        }

        if (ancestor.getLeft() == parent && parent.getLeft() == newNode) { // Left-Left
            ancestor.setLeft(parent.getRight());
            if (grandAncestor == null){
                root = parent;
            }
            else if (grandAncestor.getLeft() == ancestor) {
                grandAncestor.setLeft(parent);
            } else {
                grandAncestor.setRight(parent);
            }

            parent.setRight(ancestor);
            parent.setColor("black");
            ancestor.setColor("red");
            return parent;
        } else if (ancestor.getLeft() == parent && parent.getRight() == newNode) { // Left-Right
            parent.setRight(newNode.getLeft());
            ancestor.setLeft(newNode.getRight());
            newNode.setRight(ancestor);
            newNode.setLeft(parent);
            if (grandAncestor == null){
                root = newNode;
            }
            else if (grandAncestor.getLeft() == ancestor) {
                grandAncestor.setLeft(newNode);
            } else {
                grandAncestor.setRight(newNode);
            }
            newNode.setColor("black");
            parent.setColor("red");
            ancestor.setColor("red");
            return newNode;
        } else if (ancestor.getRight() == parent && parent.getLeft() == newNode) { // Right-Left
            parent.setLeft(newNode.getRight());
            ancestor.setRight(newNode.getLeft());
            newNode.setLeft(ancestor);
            newNode.setRight(parent);
            if (grandAncestor == null){
                root = newNode;
            }
            else if (grandAncestor.getLeft() == ancestor) {
                grandAncestor.setLeft(newNode);
            } else {
                grandAncestor.setRight(newNode);
            }
            newNode.setColor("black");
            parent.setColor("red");
            ancestor.setColor("red");
            return newNode;
        } else { // Right-Right
            ancestor.setRight(parent.getLeft());
            if (grandAncestor == null){
                root = newNode;
            }
            else if (grandAncestor.getLeft() == ancestor) {
                grandAncestor.setLeft(parent);
            } else {
                grandAncestor.setRight(parent);
            }
            parent.setLeft(ancestor);
            parent.setColor("black");
            ancestor.setColor("red");
            newNode.setColor("red");
            return parent;
        }
    }


    public Node reColoring(Node node, Node parent, Node ancestor, Node uncle) {
        // 부모와 삼촌 노드를 black으로 변경
        parent.setColor("black");
        uncle.setColor("black");
        // 조상 노드를 red로 변경
        ancestor.setColor("red");

        // 만약에 조상 노드가 root면 black으로 변경
        if (ancestor == root) {
            ancestor.setColor("black");
        }

        return ancestor;
    }
}
