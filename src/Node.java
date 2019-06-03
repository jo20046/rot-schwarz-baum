class Node {

    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private String content;
    private int color = ColorEnum.BLACK;

    Node (String content) {
        this.setContent(content);
    }


    Node getParent() {
        return parent;
    }

    void setParent(Node parent) {
        this.parent = parent;
    }

    Node getLeftChild() {
        return leftChild;
    }

    void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    Node getRightChild() {
        return rightChild;
    }

    void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    boolean hasLeftChild() {
        return leftChild != null;
    }

    boolean hasRightChild() {
        return rightChild != null;
    }

    int getColor() {
        return color;
    }

    boolean setColor(int color) {
        if (color == ColorEnum.BLACK || color == ColorEnum.RED) {
            this.color = color;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getContent() + "; " + getColor();
    }
}
