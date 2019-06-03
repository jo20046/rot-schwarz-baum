class Node {

    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private String content;

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
}
