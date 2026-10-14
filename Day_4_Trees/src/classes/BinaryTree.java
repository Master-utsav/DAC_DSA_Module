package classes;

import interfaces.BinaryTreeInterface;

import java.util.Objects;

public class BinaryTree<T> implements BinaryTreeInterface<T> {


    private TreeNode<T> root = null;

    @Override
    public void setRoot(TreeNode<T> rootNode){
        root = rootNode;
    }

    @Override
    public String inOrderTraversal() {
        TreeNode<T> rootNode = root;
        StringBuilder strB = new StringBuilder();
        strB.append("[");
        if(rootNode == null){
            strB.append("]");
            return strB.toString();
        }
        inorder(strB, rootNode);
        strB.append("]");
        return strB.toString();
    }

    private void inorder(StringBuilder str, TreeNode<T> rootNode){

        if(rootNode == null) {
            return;
        };

        // left
        if(rootNode.left != null){
            inorder(str, rootNode.left);
        }
        // process
        str.append(rootNode.data).append(", ");

        // right
        if(rootNode.right != null){
            inorder(str, rootNode.right);
        }

    }


    @Override
    public String preOrderTraversal() {
        TreeNode<T> rootNode = root;
        StringBuilder strB = new StringBuilder();
        strB.append("[");
        if(rootNode == null){
            strB.append("]");
            return strB.toString();
        }
        preOrder(strB, rootNode);
        strB.append("]");
        return strB.toString();
    }

    private void preOrder(StringBuilder str, TreeNode<T> rootNode){

        if(rootNode == null) {
            return;
        };

        // process
        str.append(rootNode.data).append(", ");

        // left
        if(rootNode.left != null){
            inorder(str, rootNode.left);
        }

        // right
        if(rootNode.right != null){
            inorder(str, rootNode.right);
        }
    }

    @Override
    public String postOrderTraversal() {
        TreeNode<T> rootNode = root;
        StringBuilder strB = new StringBuilder();
        strB.append("[");
        if(rootNode == null){
            strB.append("]");
            return strB.toString();
        }
        postOrder(strB, rootNode);
        strB.append("]");
        return strB.toString();
    }

    private void postOrder(StringBuilder str, TreeNode<T> rootNode){

        if(rootNode == null) {
            return;
        };


        // left
        if(rootNode.left != null){
            inorder(str, rootNode.left);
        }
        // right
        if(rootNode.right != null){
            inorder(str, rootNode.right);
        }
        // process
        str.append(rootNode.data).append(", ");


    }


    @Override
    public int countNode() {
        int count = 0;
        TreeNode<T> rootNode = root;
        if(rootNode == null) return count;
        inorder(count, rootNode);
        return count;
    }

    private void inorder(int count, TreeNode<T> rootNode){

        if(rootNode == null) return;

        // left
        if(rootNode.left != null){
            inorder(count, rootNode.left);
        }
        // process
        count++;

        // right
        if(rootNode.right != null){
            inorder(count, rootNode.right);
        }

    }

    @Override
    public int countLeafNodes() {
        TreeNode<T> rootNode = root;
        int countLeafs = 0;
        if(rootNode == null) return countLeafs;
        inorder(countLeafs, rootNode);
        return countLeafs;
    }

    private void inorderLeafCount(int countLeafs, TreeNode<T> rootNode){

        if(rootNode == null) return;

        // left
        if(rootNode.left != null){
            inorder(countLeafs, rootNode.left);
        }
        if(rootNode.left == null && rootNode.right == null){
            countLeafs++;
        }

        // right
        if(rootNode.right != null){
            inorder(countLeafs, rootNode.right);
        }

    }

    @Override
    public int countNodesWithValues(T value) {
        TreeNode<T> rootNode = root;
        int count = 0;
        if(rootNode == null) return count;
        inorder(count, rootNode);
        return count;
    }

    private void inorderCountContainValue(int count, TreeNode<T> rootNode , T value){

        if(rootNode == null) return;

        // left
        if(rootNode.left != null){
            inorder(count, rootNode.left);
        }
        if(Objects.equals(rootNode.data, value)){
            count++;
        }

        // right
        if(rootNode.right != null){
            inorder(count, rootNode.right);
        }

    }
}
