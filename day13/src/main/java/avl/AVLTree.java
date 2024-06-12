package avl;

public class AVLTree {
    AVLNode root;

    int height(AVLNode node) {
        if (node == null)
            return -1;
        return node.height;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private AVLNode leftRotate(AVLNode parent) {
        AVLNode newParent = parent.right;
        AVLNode T2 = newParent.left;

        newParent.left = parent;
        parent.right = T2;

        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;

        return newParent;
    }

    private AVLNode rightRotate(AVLNode parent) {
        AVLNode newParent = parent.left;
        AVLNode T2 = newParent.right;

        newParent.right = parent;
        parent.left = T2;

        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;

        return newParent;
    }

    public void insert(Integer data) {
        this.root = insert(this.root, data);
    }

    private AVLNode insert(AVLNode node, Integer data) {

        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        if (balance > 1 && node.left.data < data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
}
