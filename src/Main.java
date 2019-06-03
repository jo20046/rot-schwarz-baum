public class Main {
    public static void main(String[] args) {

        Node root = new Node("root");

        Node left = new Node("left");
        left.setParent(root);
        root.setLeftChild(left);

        Node right = new Node("right");
        right.setParent(root);
        root.setRightChild(right);


        int i = 0;
    }
}
