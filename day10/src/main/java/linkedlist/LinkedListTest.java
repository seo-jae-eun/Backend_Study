package linkedlist;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertLast(19);
        linkedList.insertLast(25);
        linkedList.insertLast(30);
        linkedList.insertLast(40);
        linkedList.insertLast(65);
        linkedList.insertLast(87);
        linkedList.insertLast(1);
        linkedList.insert(50, 2);

        linkedList.deleteLast();
        linkedList.deleteLast();
        linkedList.deleteLast();

        linkedList.print();

    }
}
