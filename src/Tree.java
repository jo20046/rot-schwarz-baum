class Tree {

    private Node root;

    Tree() {
    }

    Tree(Node root) {
        this.setRoot(root);
    }

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

    void print() {
        if (!isEmpty()) {
            printNode(getRoot());
        }
    }

    private void printNode(Node node) {
        System.out.println(node.getContent());
        if (node.hasLeftChild()) {
            printNode(node.getLeftChild());
        }
        if (node.hasRightChild()) {
            printNode(node.getRightChild());
        }
    }

    boolean isEmpty() {
        return getRoot() == null;
    }

    Node getRoot() {
        return root;
    }

    void setRoot(Node root) {
        this.root = root;
    }
}
