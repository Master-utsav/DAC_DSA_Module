import classes.CustomQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomQueue<Integer> qu = new CustomQueue<>();

        for(int i = 0; i < 5; i++){
            int val = (int) (Math.random()* 999);
            System.out.println("Inserting val : " + val);
            qu.enqueue(val);
        }
        System.out.println("Is queue full Now : " + qu.isFull());
        System.out.println("******************************");

        System.out.println(qu);
        System.out.println("******************************");

        System.out.println("Dequeue the element is : " + qu.dequeue());
        System.out.println("size of the queue : " + qu.size());
        System.out.println(qu);
        System.out.println("******************************");

        while(!qu.isEmpty()){
            System.out.println("Top element is : " + qu.dequeue());
            System.out.println("size of the queue : " + qu.size());
        }
        System.out.println("********************************");

        System.out.println("Is queue empty Now : " + qu.isEmpty());
        System.out.println("********************************");
    }
}