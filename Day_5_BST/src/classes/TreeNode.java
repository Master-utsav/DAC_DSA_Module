package classes;

public class TreeNode<T>{
    public TreeNode<T> left = null;
    public TreeNode<T> right = null;
    public T data;

    public TreeNode(T data){
        this.left = null;
        this.right = null;
        this.data = data;
    }
}
