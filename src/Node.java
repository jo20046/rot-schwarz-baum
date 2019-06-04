class Node {

    private Node parent;
    private Node leftChild;
    private Node rightChild;
    /*private*/ String content;
    private int color = ColorEnum.RED;

    Node (String content) {
        this.setContent(content);
    }

    // Misc - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    boolean hasLeftChild() {
        return leftChild != null;
    }

    boolean hasRightChild() {
        return rightChild != null;
    }

    boolean isLeaf() {
        return (!hasLeftChild() && !hasRightChild());
    }

    // Getter, Setter - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
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
//        return getContent() + "; " + getColor();
        String content = getContent();
        return content.substring(0, 4) + (getColor() == ColorEnum.BLACK ? 'B' : 'R');
    }
}
