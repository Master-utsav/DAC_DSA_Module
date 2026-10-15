package classes;

import interfaces.BinarySearchTreeInterface;

import java.util.*;

public class BinarySearchTree<T> implements BinarySearchTreeInterface<T> {
    private TreeNode<T> root = null;

    @Override
    public void setRoot(TreeNode<T> rootNode){
        root = rootNode;
    }

    @Override
    public void insert(T data, Comparator<T> comparator) {
        TreeNode<T> rootNode = root;
        TreeNode<T> newNode = new TreeNode<>(data);
        if(rootNode == null){
            setRoot(newNode);
            return;
        }

        while(true){
            int cmp = comparator.compare(rootNode.data, data);
            if(cmp == 0) return;
            else if(cmp > 0){
              if(rootNode.left == null){
                  rootNode.left = newNode;
                  return;
              }
              rootNode = rootNode.left;
            }
            else {
                if(rootNode.right == null){
                    rootNode.right = newNode;
                    return;
                }
                rootNode = rootNode.right;
            }
        }
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
        StringBuilder strB = new StringBuilder("[");
        TreeNode<T> rootNode = root;
        if(rootNode == null){
            strB.append("]");
            return strB.toString();
        }
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

    @Override
    public String levelOrderTraversalPerLevel() {
        StringBuilder strB = new StringBuilder();
        TreeNode<T> rootNode = root;
        if(rootNode == null){
            strB.append("[]");
            return strB.toString();
        }
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
    public boolean contains(T data, Comparator<T> comparator) {
        TreeNode<T> rootNode = root;

        while(rootNode != null){
            int cmp = comparator.compare(rootNode.data, data);
            if(cmp == 0) return true;
            if(cmp > 0) rootNode = rootNode.left;
            else rootNode = rootNode.right;
        }
        return false;
    }

    @Override
    public int height() {
        TreeNode<T> rootNode = root;
        if(rootNode == null) return 0;
        return findHeight(rootNode);
    }
    private int findHeight(TreeNode<T> rootNode){
        if(rootNode == null) return -1;
        return 1 + Integer.max(findHeight(rootNode.left) , findHeight(rootNode.right));
    }

    @Override
    public T maxElement() {
        TreeNode<T> rootNode = root;
        T maxEle = null;
        if(rootNode == null) return null;
        while(rootNode != null){
            maxEle = rootNode.data;
            rootNode = rootNode.right;
        }
        return maxEle;
    }

    @Override
    public T minElement() {
        TreeNode<T> rootNode = root;
        T minEle = null;
        if(rootNode == null) return null;
        while(rootNode != null){
            minEle = rootNode.data;
            rootNode = rootNode.left;
        }
        return minEle;
    }

    @Override
    public TreeNode<T> inOrderPredecessor(T value, Comparator<T> comparator) {
        TreeNode<T> rootNode = root;
        TreeNode<T> previousNode = null;

        while(rootNode != null){

            int cmp = comparator.compare(rootNode.data, value);
            if(cmp == 0) break;
            else if(cmp > 0){
                rootNode = rootNode.left;
            }
            else{
                rootNode = rootNode.right;
            }
            previousNode = rootNode;

        }

        System.out.println(previousNode.data);
        TreeNode<T> maxEle = null;
        if(rootNode == null) return previousNode;
        if(rootNode.left != null){
            rootNode = rootNode.left;
            while(rootNode != null){
                maxEle = rootNode;
                rootNode = rootNode.right;
            }
        }
        if(maxEle == null) return previousNode;
        return maxEle;
    }

    @Override
    public TreeNode<T> inOrderSuccessor(T value, Comparator<T> comparator) {
        TreeNode<T> rootNode = root;
        TreeNode<T> previousNode = null;

        while(rootNode != null){
            int cmp = comparator.compare(rootNode.data, value);
            if(cmp == 0) break;
            else if(cmp > 0){
                rootNode = rootNode.left;
            }
            else{
                rootNode = rootNode.right;
            }
            previousNode = rootNode;
        }

        TreeNode<T> minEle = null;
        if(rootNode == null) return previousNode;
        if(rootNode.right != null){
            rootNode = rootNode.right;
            while(rootNode != null){
                minEle = rootNode;
                rootNode = rootNode.left;
            }
        }
        if(minEle == null) return previousNode;
        return minEle;
    }

    @Override
    public boolean remove(T data, Comparator<T> comparator) {
        return false;
    }


}
