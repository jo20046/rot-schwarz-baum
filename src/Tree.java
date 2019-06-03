import java.awt.*;

class Tree {

    // Attributes - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node root;

    // Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    Tree() {
    }

    Tree(Node root) {
        this.setRoot(root);
    }

    // Insert - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    void insert(Node newNode) {
        if (isEmpty()) {
            setRoot(newNode);
        } else {
            insertNode(newNode, getRoot());
        }
    }

    private void insertNode(Node newNode, Node parent) {
        if (newNode.getContent().compareToIgnoreCase(parent.getContent()) < 0) {
            if (!parent.hasLeftChild()) {
                parent.setLeftChild(newNode);
                newNode.setParent(parent);
            } else {
                insertNode(newNode, parent.getLeftChild());
            }
        } else {
            if (!parent.hasRightChild()) {
                parent.setRightChild(newNode);
                newNode.setParent(parent);
            } else {
                insertNode(newNode, parent.getRightChild());
            }
        }
    }

    // Print - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    void print() {
        print(TraversalEnum.PREORDER);
    }

    void print(int traversingMethod) {
        if (!isEmpty()) {
            switch (traversingMethod) {
                case TraversalEnum.PREORDER:
                    preorder(getRoot());
                    break;
                case TraversalEnum.INORDER:
                    inorder(getRoot());
                    break;
                case TraversalEnum.POSTORDER:
                    postorder(getRoot());
                    break;
                default:
                    break;
            }
        }
    }

    private void preorder(Node node) {
        if (node != null) {
            System.out.println(node.toString());
            preorder(node.getLeftChild());
            preorder(node.getRightChild());
        }
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.getLeftChild());
            System.out.println(node.toString());
            inorder(node.getRightChild());
        }
    }

    private void postorder(Node node) {
        if (node != null) {
            postorder(node.getLeftChild());
            postorder(node.getRightChild());
            System.out.println(node.toString());
        }
    }

    // Fix Colors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    void fixColors() {
        makeRootAndLeavesBlack(getRoot());


    }

    private void makeRootAndLeavesBlack(Node node) {
        if (node != null) {
            if (node.equals(getRoot()) || node.isLeaf()) {
                node.setColor(ColorEnum.BLACK);
            }
            makeRootAndLeavesBlack(node.getLeftChild());
            makeRootAndLeavesBlack(node.getRightChild());
        }
    }

    int getMinimumDepth(int currentDepth, Node node) {
        if (!node.hasLeftChild() || !node.hasRightChild()) {
            return ++currentDepth;
        }
        int leftDepth = getMinimumDepth(++currentDepth, node.getLeftChild());
        int rightDepth = getMinimumDepth(++currentDepth, node.getRightChild());
        return leftDepth < rightDepth ? leftDepth : rightDepth;
    }

    // Misc - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    boolean isEmpty() {
        return getRoot() == null;
    }

    // Getter, Setter - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    Node getRoot() {
        return root;
    }

    void setRoot(Node root) {
        this.root = root;
    }
}
