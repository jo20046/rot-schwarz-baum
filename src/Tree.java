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
        System.out.println(node.getContent());
        if (node.hasLeftChild()) {
            preorder(node.getLeftChild());
        }
        if (node.hasRightChild()) {
            preorder(node.getRightChild());
        }
    }

    private void inorder(Node node) {
        if (node.hasLeftChild()) {
            inorder(node.getLeftChild());
        }
        System.out.println(node.getContent());
        if (node.hasRightChild()) {
            inorder(node.getRightChild());
        }
    }

    private void postorder(Node node) {
        if (node.hasLeftChild()) {
            postorder(node.getLeftChild());
        }
        if (node.hasRightChild()) {
            postorder(node.getRightChild());
        }
        System.out.println(node.getContent());
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
