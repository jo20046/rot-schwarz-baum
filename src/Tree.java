import java.util.ArrayList;
import java.util.List;

class Tree {

    // Attributes - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private Node root;

    // Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    Tree() {
    }

    Tree(Node root) {
        this.insert(root);
    }

    // Insert - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    void insert(Node newNode) {
        if (isEmpty()) {
            this.root = newNode;
            newNode.setParent(null);
        } else {
            insertNode(newNode, getRoot());
        }
        newNode.setLeftChild(null);
        newNode.setRightChild(null);
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
        makeEverythingRed(getRoot());
        makeRootAndLeavesBlack(getRoot());
        makeChildrenBlack(getRoot());

    }

    private void makeEverythingRed(Node node) {
        if (node != null) {
            node.setColor(ColorEnum.RED);
            makeEverythingRed(node.getLeftChild());
            makeEverythingRed(node.getRightChild());
        }
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

    private void makeChildrenBlack(Node node) {
        if (node != null) {
            if (node.getColor() == ColorEnum.RED) {
                if (node.hasLeftChild()) {
                    node.getLeftChild().setColor(ColorEnum.BLACK);
                }
                if (node.hasRightChild()) {
                    node.getRightChild().setColor(ColorEnum.BLACK);
                }
            }
            makeChildrenBlack(node.getLeftChild());
            makeChildrenBlack(node.getRightChild());
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

    // Balance - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    Tree balanced() {
        Tree unbalancedTree = this;
        Tree balancedTree;
        while(true) {
            int oldBalance = unbalancedTree.getBalance();
            if (oldBalance == 0) {
                return this;
            }
            if (oldBalance > 0) {
                balancedTree = unbalancedTree.rotate(RotationEnum.CLOCKWISE);
            } else {
                balancedTree = unbalancedTree.rotate(RotationEnum.COUNTER_CLOCKWISE);
            }
            int newBalance = balancedTree.getBalance();
            if (newBalance == 0) {
                return balancedTree;
            }
            if ((newBalance > 0 && oldBalance < 0) || (newBalance < 0 && oldBalance > 0)) {
                return Math.abs(newBalance) < Math.abs(oldBalance) ? balancedTree : unbalancedTree;
            }
            unbalancedTree = balancedTree;
            unbalancedTree.display();
        }
    }

    private int getBalance() {
        return countChildrenR(0, this.getRoot().getLeftChild()) - countChildrenR(0, this.getRoot().getRightChild());
    }

    private int countChildrenR(int ret, Node node) {
        if (node != null) {
            int left = countChildrenR(ret, node.getLeftChild());
            int right = countChildrenR(ret, node.getRightChild());
            ret = left + right + 1;
        }
        return ret;
    }

    // Rotation - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    Tree rotate(int direction) {
        Tree newTree = new Tree();
        Node newRoot = new Node();
        switch (direction) {
            case RotationEnum.CLOCKWISE:
                newRoot = this.getRoot().getLeftChild();
                break;
            case RotationEnum.COUNTER_CLOCKWISE:
                newRoot = this.getRoot().getRightChild();
                break;
            default:
                break;
        }
        List<Node> nodesToInsert = getAllNodesExceptNewRoot(newRoot);
        newTree.insert(newRoot);
        for (Node node : nodesToInsert) {
            newTree.insert(node);
        }
        newTree.fixColors();
        return newTree;
    }

    private List<Node> getAllNodesExceptNewRoot(Node newRoot) {
        return getNodes(new ArrayList<>(), this.getRoot(), newRoot);
    }

    private List<Node> getNodes(List<Node> resultList, Node current, Node newRoot) {
        if (current != null) {
            if (!current.equals(newRoot)) {
                resultList.add(current);
            }
            getNodes(resultList, current.getLeftChild(), newRoot);
            getNodes(resultList, current.getRightChild(), newRoot);
        }
        return resultList;
    }

    // Display - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    void display() {
        final int height = 8, width = 128;

        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, this.getRoot(), " ");
        System.out.println(sb);
    }

    private void displayR(StringBuilder sb, int c, int r, int d, int w, Node node,
                          String edge) {
        if (node != null) {
            displayR(sb, c - d, r + 2, d / 2, w, node.getLeftChild(), " /");

//            String s = String.valueOf(node.key);
            String s = node.toString();
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, c + d, r + 2, d / 2, w, node.getRightChild(), "\\ ");
        }
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
