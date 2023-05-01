package MyApp;

public class MyApp {

    public static void main(String[] args) {
/*
*       50
*   40       60
* 30  45   55   70
* */




    }

    static class Node {
        final int key;
        Node left, right;

        Node(int item) {
            key = item;
            left = right = null;
        }
    }

    public static Node search(int key, Node root) {
        if (root == null) return null;
        if (key == root.key) return root;
        return search(key, key > root.key ? root.right : root.left);
    }


}
