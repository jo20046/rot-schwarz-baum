public class Main {
    public static void main(String[] args) {

        Tree tree = new Tree();
        Node root = new Node("root");
        tree.setRoot(root);

        Node left = new Node("left");
        left.setParent(root);
        root.setLeftChild(left);

        Node right = new Node("right");
        right.setParent(root);
        root.setRightChild(right);

        tree.print();
        int i = 0;
    }
}
