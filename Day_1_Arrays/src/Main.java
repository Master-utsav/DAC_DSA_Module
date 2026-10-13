public class Main {
    public static void main(String[] args) {
        CustomArray<Integer> arrList = new CustomArray<>(4);

        System.out.println("Array Capacity is : " + arrList.capacity());
        System.out.println("******************************");
        for(int i = 0; i < arrList.capacity(); i++){
            int val = (int) (Math.random()* 999);
            System.out.println("Inserting val : " + val);
            arrList.add(val);
        }
        System.out.println("Is Array full Now : " + arrList.isFull());
        System.out.println("******************************");

        System.out.println(arrList);
        System.out.println("******************************");

        System.out.println("size of the array : " + arrList.size());
        arrList.resize(8);
        System.out.println("Array New Capacity is : " + arrList.capacity());
        System.out.println("******************************");

        arrList.remove(arrList.get(2)); // true
        System.out.println("size of the array after removing the element based on value of arr[2] : " + arrList.size());
        System.out.println(arrList);
        System.out.println("******************************");

        arrList.add(100, 2);
        System.out.println("after adding 100 at pos 2 size of the array : " + arrList.size());
        System.out.println(arrList);
        System.out.println("******************************");

        System.out.println(arrList);
        System.out.println("********************************");

        int newSize = arrList.deleteFromPosition(3);
        System.out.println("New size of array is " + newSize);
        System.out.println(arrList);
        System.out.println("****************************");
    }
}