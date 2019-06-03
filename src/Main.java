public class Main {
    public static void main(String[] args) {

        Node root = new Node("root");
        Tree tree = new Tree(root);

        Node left = new Node("left");
        tree.insert(left);

        Node right = new Node("right");
        tree.insert(right);

        tree.print();
        int i = 0;
    }
}
