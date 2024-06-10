package linkedlist;

public class LinkedList {
    // 가장 먼저 추가한 노드를 저장하는 변수 head
    private Node head;
    // 마지막에 추가한 노드를 저장하는 변수 tail
    private Node tail;

    // 연결리스트를 처음에 생성할 때
    //  head에 null을 저장
    //  tail에 null을 저장

    public LinkedList() {
        head = null;
        tail = null;
    }

    // 숫자 하나와 순서번호 하나를 전달받아서 순서번호에 추가하는 기능
    //  만약에 순서번호가 0이면
    //      숫자를 하나 전달받아서 맨 처음에 추가하는 기능 실행하고
    //      종료   종료는 return; 하면됨
    //  만약에 순서번호가 현재 저장한 데이터의 수와 같으면
    //      숫자를 하나 전달받아서 마지막에 추가하는 기능 실행하고
    //      종료   종료는 return; 하면됨
    //  만약에 저장된 데이터 수보다 크면 순서번호에 저장하려고하면
    //      저장 못함이라고 출력
    //  그렇지 않으면
    //      노드 생성를 생성해서 변수에 저장
    //      생성한 노드의 데이터에 전달받은 숫자를 저장
    //      현재 노드를 저장할 변수 생성
    //      현재 노드를 저장할 변수에 head를 저장
    //      입력받은 순서번호 -1만큼 반복
    //          현재 노드에 현재 노드의 다음 노드를 저장
    //      생성한 노드의 다음 노드에 현재노드의 다음 노드를 저장
    //      현재 노드의 다음 노드에 생성한 노드를 저장
    public void insert(Integer number, Integer index) {
        Integer size = 1;
        if(index == 0) {
            insertFirst(number);
            return;
        }

        Node sizeNode = head;
        while(true) {
            if(sizeNode.getNextNode() != null) {
                sizeNode = sizeNode.getNextNode();
                size++;
            } else {
                break;
            }
        }
        if(index == size) {
            insertLast(number);
            return;
        }
        if(index > size) {
            System.out.println("저장 못함");
        } else {
            Node node = new Node(number);
            Node currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            node.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(node);
        }
    }


    // 숫자를 하나 전달받아서 맨 처음에 추가하는 기능
    //  만약에 저장한 적이 있으면
    //      노드 생성를 생성해서 변수에 저장
    //      생성한 노드의 데이터에 전달받은 숫자를 저장
    //      생성한 노드의 다음 노드에 head를 저장
    //      head에 생성한 노드를 저장
    //  그렇지 않으면
    //      노드 생성를 생성해서 변수에 저장
    //      생성한 노드의 데이터에 전달받은 숫자를 저장
    //      tail에 생성한 노드 저장
    //      head에 생성한 노드 저장
    public void insertFirst(Integer number) {
        Node node = new Node(number);
        if(tail != null) {
//            Node node = new Node(number);
            node.setNextNode(head);
            head = node;
        }
        else {
//            Node node = new Node(number);
            tail = node;
            head = node;
        }
    }


    // 숫자를 하나 전달받는 마지막에 추가하는 기능
    //  만약에 저장한 적이 있으면
    //      노드를 생성해서 변수에 저장
    //      생성한 노드의 데이터에 전달받은 숫자를 저장
    //      tail의 다음 노드에 현재 생성한 노드를 저장
    //      tail에 현재 생성한 노드 저장
    //  그렇지 않으면
    //      노드를 생성해서 변수에 저장
    //      생성한 노드의 데이터에 전달받은 숫자를 저장
    //      tail에 생성한 노드 저장
    //      head에 생성한 노드 저장
    public void insertLast(Integer number) {
        Node node = new Node(number);
        if(tail != null) {
//            Node node = new Node(number);
            tail.setNextNode(node);
            tail = node;
        }
        else {
//            Node node = new Node(number);
            tail = node;
            head = node;
        }
    }

    // 연결리스트에 저장된 모든 데이터를 출력하는 기능 print
    //  처음 노드의 데이터 출력 다음 노드의 데이터 출력
    //      현재 노드를 저장한 변수 생성
    //      현재 노드를 저장할 변수에 head를 저장
    //      현재 노드를 저장할 변수가 비어있지 않으면 반복
    //          현재 노드의 데이터를 출력
    //          현재 노드에 현재 노드의 다음 노드를 저장

    public void print() {
        Node node = head;
        while(node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNextNode();
        }

    }

    // 삭제하는 기능
    public void deleteLast() {
        if(tail != null) {
            Integer size = 1;

            Node sizeNode = head;
            while(true) {
                if(sizeNode.getNextNode() != null) {
                    sizeNode = sizeNode.getNextNode();
                    size++;
                } else {
                    break;
                }
            }
            Node currentNode = head;
            for(int i = 0; i < size - 2; i++) {
                currentNode = currentNode.getNextNode();
            }
            tail = currentNode;
            currentNode.setNextNode(null);

        }
        else {
            System.out.println("삭제할거없음");
        }
    }
    public void deleteFirst() {
        if(tail != null) {
            head = head.getNextNode();
        }
        else {
            System.out.println("삭제 못함");
        }
    }

    public void delete(Integer index) {
        Integer size = 1;
        if(index == 0) {
            deleteFirst();
            return;
        }

        Node sizeNode = head;
        while(true) {
            if(sizeNode.getNextNode() != null) {
                sizeNode = sizeNode.getNextNode();
                size++;
            } else {
                break;
            }
        }
        if(index == size) {
            deleteLast();
            return;
        }
        if(index > size) {
            System.out.println("삭제 못함");
        } else {
            Node currentNode = head;
            Node deleteNode = head;
            for(int i = 0; i < index; i++) {
                deleteNode = deleteNode.getNextNode();
            }
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(deleteNode.getNextNode());
        }

    }
}
