package classes;

import interfaces.BinaryTreeInterface;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinaryTree<T> implements BinaryTreeInterface<T> {

    private TreeNode<T> root = null;

    @Override
    public void setRoot(TreeNode<T> rootNode){
        root = rootNode;
    }


    @Override
    public String inOrderTraversal() {
        if(root == null) return "[]";

        TreeNode<T> rootNode = root;
        StringBuilder strB = new StringBuilder("[");

        inorder(strB, rootNode);

        strB.append("]");
        return strB.toString();
    }
    private void inorder(StringBuilder str, TreeNode<T> rootNode){
        if(rootNode == null) return;
        // left
        if(rootNode.left != null) inorder(str, rootNode.left);
        // process
        str.append(rootNode.data).append(", ");
        // right
        if(rootNode.right != null) inorder(str, rootNode.right);
    }

    @Override
    public String preOrderTraversal() {
        if(root == null) return "[]";

        TreeNode<T> rootNode = root;
        StringBuilder strB = new StringBuilder("[");

        preOrder(strB, rootNode);

        strB.append("]");
        return strB.toString();
    }
    private void preOrder(StringBuilder str, TreeNode<T> rootNode){
        if(rootNode == null) return;
        // process
        str.append(rootNode.data).append(", ");
        // left
        if(rootNode.left != null) preOrder(str, rootNode.left);
        // right
        if(rootNode.right != null) preOrder(str, rootNode.right);
    }

    @Override
    public String postOrderTraversal() {
        if(root == null) return "[]";

        TreeNode<T> rootNode = root;
        StringBuilder strB = new StringBuilder("[");

        postOrder(strB, rootNode);

        strB.append("]");
        return strB.toString();
    }
    private void postOrder(StringBuilder str, TreeNode<T> rootNode){
        if(rootNode == null) return;
        // left
        if(rootNode.left != null) postOrder(str, rootNode.left);
        // right
        if(rootNode.right != null) postOrder(str, rootNode.right);
        // process
        str.append(rootNode.data).append(", ");
    }

    @Override
    public String levelOrderTraversal() {
        if(root == null) return "[]";

        StringBuilder strB = new StringBuilder("[");
        TreeNode<T> rootNode = root;

        Queue<TreeNode<T>> tempQueue = new LinkedList<>();
        tempQueue.add(rootNode);

        while(!tempQueue.isEmpty()){
            TreeNode<T> temp = tempQueue.poll();
            strB.append(temp.data).append(", ");
            if(temp.left != null) tempQueue.add(temp.left);
            if(temp.right != null) tempQueue.add(temp.right);
        }

        strB.append("]");

        return strB.toString();
    }


//    Overall timeComplexity O(N2)
//    @Override
//    public String levelOrderTraversalPerLevel() {
//        StringBuilder strB = new StringBuilder();
//        TreeNode<T> rootNode = root;
//        if(rootNode == null){
//            strB.append("[]");
//            return strB.toString();
//        }
//        // algo
//        Queue<TreeNode<T>> tempQueue = new LinkedList<>();
//        tempQueue.add(rootNode);
//        tempQueue.add(null);
//
//        while(!tempQueue.isEmpty()){
//            TreeNode<T> temp = tempQueue.poll();
//            if(temp == null) strB.append("\n");
//            else strB.append(temp.data).append(", ");
//            if(temp != null && temp.left != null) tempQueue.add(temp.left);
//            if(temp != null && temp.right != null) tempQueue.add(temp.right);
//            if(!tempQueue.contains(null) && !tempQueue.isEmpty()) tempQueue.add(null); // O(N)
//        }
//        return strB.toString();
//    }

    @Override
    public String levelOrderTraversalPerLevel() {
        if(root == null) return "[]";

        StringBuilder strB = new StringBuilder();
        TreeNode<T> rootNode = root;

        // algo
        Queue<TreeNode<T>> tempQueue = new LinkedList<>();
        tempQueue.add(rootNode);

        while(!tempQueue.isEmpty()){
            int size = tempQueue.size();

            for(int i = 0; i < size; i++){
                TreeNode<T> temp = tempQueue.poll();
                if(temp != null){
                    strB.append(temp.data).append(", ");
                }
                if(temp != null && temp.left != null) tempQueue.add(temp.left);
                if(temp != null && temp.right != null) tempQueue.add(temp.right);
            }

            strB.append("\n");
        }

        return strB.toString();
    }


    @Override
    public int countNode() {
        return countNodes(root);
    }
    private int countNodes(TreeNode<T> rootNode){
        if(rootNode == null) return 0;
        return 1 + countNodes(rootNode.left) + countNodes(rootNode.right);
    }

    @Override
    public int countLeafNodes() {
        return countLeaf(root);
    }
    private int countLeaf(TreeNode<T> rootNode){
        if(rootNode == null) return 0;
        int ifLeafThenAdd = (rootNode.left == null && rootNode.right == null) ? 1 : 0;
        return ifLeafThenAdd + countLeaf(rootNode.left) + countLeaf(rootNode.right);
    }

    @Override
    public int countNodesWithValues(T value) {
        return countContainValue(root , value);
    }
    private int countContainValue(TreeNode<T> rootNode , T value){
        if(rootNode == null) return 0;
        int ifMatchThenAdd = Objects.equals(rootNode.data, value)  ? 1 : 0;
        return ifMatchThenAdd + countContainValue(rootNode.left, value) + countContainValue(rootNode.right, value);
    }
}
