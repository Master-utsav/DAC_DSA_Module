package interfaces;

import classes.BinaryTree;
import classes.TreeNode;

public interface BinaryTreeInterface<T> {
    public String inOrderTraversal();
    public String preOrderTraversal();
    public String postOrderTraversal();

    public String levelOrderTraversal();
    public String levelOrderTraversalPerLevel();

    public void setRoot(TreeNode<T> rootNode);

    public int countNode();
    public int countLeafNodes();
    public int countNodesWithValues(T value);

    default String boundaryTraversal(){
        return "";
    }

}
