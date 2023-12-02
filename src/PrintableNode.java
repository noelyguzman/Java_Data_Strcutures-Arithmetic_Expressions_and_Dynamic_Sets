public interface PrintableNode {
    /**
     * @return the value of this node as a string
     */
    String getValueAsString();
    /**
     * @return the left child node of this node
     */
    PrintableNode getLeft();
    /**
     * @return the right child node of this node
     */
    PrintableNode getRight();

}
