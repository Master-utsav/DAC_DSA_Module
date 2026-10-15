package interfaces;

import classes.TreeNode;

import java.util.Comparator;

public interface BinarySearchTreeInterface<T> {
    public void setRoot(TreeNode<T> rootNode);

    public void insert(T data);
    public boolean contains(T data);
    public int height();
    public T maxElement();
    public T minElement();

    public String inOrderTraversal();
    public String preOrderTraversal();
    public String postOrderTraversal();

    public boolean remove(T data);

    public String levelOrderTraversal();
    public String levelOrderTraversalPerLevel();

    public T inOrderPredecessor(T value);
    public T inOrderSuccessor(T value);

    public int countNode();
    public int countLeafNodes();
    public int countNodesWithValues(T value);
}
