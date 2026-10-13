import classes.CustomStack;

public class Main {
    public static void main(String[] args) {
        CustomStack<Integer> st = new CustomStack<>();

        for(int i = 0; i < 5; i++){
            int val = (int) (Math.random()* 999);
            System.out.println("Inserting val : " + val);
            st.push(val);
        }
        System.out.println("Is stack full Now : " + st.isFull());
        System.out.println("******************************");

        System.out.println(st);
        System.out.println("******************************");

        System.out.println("size of the stack : " + st.size());
        try {
            System.out.println("Top element is : " + st.peek());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println("size of the stack : " + st.size());
        System.out.println("******************************");

        System.out.println("size of the stack : " + st.size());
        System.out.println("Top element is : " + st.pop());
        System.out.println("size of the stack : " + st.size());
        System.out.println("********************************");

        while(!st.isEmpty()){
            System.out.println("Top element is : " + st.pop());
            System.out.println("size of the stack : " + st.size());
        }
        System.out.println("********************************");

        System.out.println("Is stack empty Now : " + st.isEmpty());
        System.out.println("********************************");
    }
}