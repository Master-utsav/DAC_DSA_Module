package classes;

import interfaces.BinarySearchTreeInterface;

import java.util.*;

public class BinarySearchTree<T> implements BinarySearchTreeInterface<T> {
    private TreeNode<T> root = null;
    private final Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public void setRoot(TreeNode<T> rootNode){
        root = rootNode;
    }

    @Override
    public void insert(T data) {
        TreeNode<T> rootNode = root;
        TreeNode<T> newNode = new TreeNode<>(data);

        if(rootNode == null){
            setRoot(newNode);
            return;
        }

        while(true){
            int cmp = comparator.compare(rootNode.data, data);
//            if(cmp == 0) return;
            if(cmp > 0){
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
    public boolean contains(T data) {
        if(root == null) return false;
        TreeNode<T> rootNode = root;

        while(rootNode != null){
            int cmp = comparator.compare(rootNode.data, data);
            if(cmp == 0) return true;
            rootNode = cmp > 0 ? rootNode.left : rootNode.right;
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
        if(root == null) return null;
        TreeNode<T> rootNode = root;
        T maxEle = null;
        while(rootNode != null){
            maxEle = rootNode.data;
            rootNode = rootNode.right;
        }
        return maxEle;
    }

    @Override
    public T minElement() {
        if(root == null) return null;
        TreeNode<T> rootNode = root;
        T minEle = null;
        while(rootNode != null){
            minEle = rootNode.data;
            rootNode = rootNode.left;
        }
        return minEle;
    }

    @Override
    public T inOrderPredecessor(T value) {
        if(root == null) return null;

        TreeNode<T> rootNode = root;
        TreeNode<T> previousNode = null;

        while (rootNode != null) {
            int cmp = comparator.compare(value, rootNode.data);

            if (cmp > 0) {
                // value is gr8 than rootNode, so rootnode is a potential predecessor here
                previousNode = rootNode;
                rootNode = rootNode.right;
            } else {
                // value is less than or equal, so explore left part of tree
                rootNode = rootNode.left;
            }
        }

        return previousNode != null ? previousNode.data : null;
    }

    @Override
    public T inOrderSuccessor(T value) {
        if(root == null) return null;

        TreeNode<T> rootNode = root;
        TreeNode<T> previousNode = null;

        while (rootNode != null) {
            int cmp = comparator.compare(value, rootNode.data);

            if (cmp >= 0) {
                // value is greater than or equal, so explore right part of tree
                rootNode = rootNode.right;
            } else {
                // value is less than rootNode, so rootnode is a potential predecessor here
                previousNode = rootNode;
                rootNode = rootNode.left;
            }
        }

        return previousNode != null ? previousNode.data : null;
    }

    @Override
    public boolean remove(T data) {
        if(root == null) return false;

        TreeNode<T> rootNode = root;
        TreeNode<T> parentNode = rootNode;

        // traverse to the node
        while(rootNode != null){
            int cmp = comparator.compare(rootNode.data , data);
            if(cmp == 0) break;
            parentNode = rootNode;
            rootNode = cmp > 0 ? rootNode.left : rootNode.right;
        }

        // if not found element
        if(rootNode == null) return false;

        // now rootnode is the element which I have to delete
        // if no left and no right children
        if(rootNode.left == null && rootNode.right == null){
            if (rootNode == root) { root = null; return true; } // Deleting root node
            // markimg the parentNode left or ight null;
            if(parentNode.left == rootNode) parentNode.left = null;
            else parentNode.right = null;
            return true;
        }

        // if only single children exist
        if(rootNode.left == null || rootNode.right == null){
            TreeNode<T> currentRootChildrenNode =
                    rootNode.left != null ?
                            rootNode.left :
                            rootNode.right;

            if (rootNode == root) {
                // Deleting root node and making troot as the left or right child;
                root = currentRootChildrenNode;
                return true;
            }
            // replacing the parentNode left or right with the childNode;
            if(parentNode.left == rootNode) parentNode.left = currentRootChildrenNode;
            else parentNode.right = currentRootChildrenNode;
            return true;
        }

        // else case when there are two childs
        // use the inorderSuccessor logic here;
        TreeNode<T> successorParent = rootNode;
        // go to the right subtree
        TreeNode<T> successor = rootNode.right;

        // go to the very left
        while(successor.left != null){
            successorParent = successor;
            successor = successor.left;
        }

        // replace the value of rootNode which is the node to be deleted
        // successor is the max value in the right subtree of the rootNode
        // so replace it
        rootNode.data = successor.data;

        // Removing successor node because -. (it has at most a right child | single)
        if(successorParent.left == successor){
            successorParent.left = successor.right;
        } else {
            successorParent.right = successor.right;
        }
        return true;
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

    public String inOrderTraversalIterative() {
        if(root == null) return "[]";

        TreeNode<T> currentNode = root;
        StringBuilder strB = new StringBuilder("[");

        Stack<TreeNode<T>> st = new Stack<>();
        while(currentNode != null || !st.isEmpty()){
            while(currentNode != null){
                st.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = st.pop();
            strB.append(currentNode.data).append(", ");
            currentNode = currentNode.right;
        }

        strB.append("]");
        return strB.toString();
    }

}
