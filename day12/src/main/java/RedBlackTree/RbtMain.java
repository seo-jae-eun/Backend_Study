package RedBlackTree;

public class RbtMain {
    public static void main(String[] args) {
        Rbt rbt = new Rbt();

        rbt.insert(50);
        rbt.insert(20);
        rbt.insert(70);
        rbt.insert(30);
        rbt.insert(40);
        rbt.insert(25);
        rbt.insert(27);
        rbt.insert(15);

        RedBlack.TreePrinter.print(rbt.getRoot());
    }
}