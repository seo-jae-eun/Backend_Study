package tree.bst;

public class Bst {
    Node root;

    public Bst() {
        this.root = null;
    }

    // add()
    // 데이터를 저장한 적이 없으면 바로 저장
    // 있으면 현재 노드와 비교해서 크면 오른쪽 작으면 왼쪽에 저장
    public void add(Integer number) {
        Node newNode = new Node(number);
        if(root == null) {
            root = newNode;
        }
        else {
            Node currentNode = root;
            while(currentNode.getData() != null) {
                if(newNode.getData() > currentNode.getData()) {
                    if(currentNode.getRight() == null) {
                        currentNode.setRight(newNode);
                        break;
                    }
                    currentNode = (Node) currentNode.getRight();
                }
                else if(newNode.getData() < currentNode.getData()) {
                    if(currentNode.getLeft() == null) {
                        currentNode.setLeft(newNode);
                        break;
                    }
                    currentNode = (Node) currentNode.getLeft();
                }
            }
        }
    }

    public void add2(Integer data) {
        if(root == null) {
            Node newNode = new Node(data);
            root = newNode;
        }
        else {
            this.addRecur(this.root, data);
        }
    }

    // 재귀
    // 어떤 노드에 어떤 데이터를 추가할건지 입력받아서
    //      만약에 특정 노드가 비어있으면
    //          노드 객체를 생성해서 데이터를 저장
    //          생성한 노드 반환
    //      만약에 데이터가 특정 노드의 데이터보다 크면
    //          특정 노드의 오른쪽에 특정 노드의 오른쪽 노드에 데이터를 추가해서 반환 받은 노드를 저장
    //      그렇지 않고 만약에 데이터가 특정 노드의 데이터보다 작으면
    //          특정 노드의 왼쪽에 특정 노드의 왼쪽에 노드에 데이터를 추가해서 반환 받은 노드를 저장
    public Node addRecur(Node node, Integer data) {
        if(node == null) {
            Node newNode = new Node(data);
            return newNode;
        }
        else {
            if(data > node.getData()) {
                node.setRight(addRecur(node.getRight(), data));
            }
            else if(data < node.getData()) {
                node.setLeft(addRecur(node.getLeft(), data));
            }
        }
        return node;
    }

    public void print() {

    }

    // remove()
    //  1. 자식이 없을 때     바로 삭제
    //  2. 자식이 하나일 때   자식이 부모로 대체
    //  3. 자식이 2개일 때    왼쪽의 서브트리에서 제일 큰 노드가 삭제되는 노드를 대체

}
