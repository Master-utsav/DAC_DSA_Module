import classes.CustomHashTable;

public class Main {
    public static void main(String[] args) {
        CustomHashTable<String, Integer> mpp = new CustomHashTable<>();

        mpp.add("utsav", 1);
        mpp.add("yousra", 1);
        mpp.add("jaiswal", 2);
        mpp.add("shaikh", 2);
        mpp.add("kind", 3);
        mpp.add("kind", 3);
        mpp.add("utsa", 1);
        mpp.add("yousr", 1);
        mpp.add("jaiswa", 2);
        mpp.add("shaik", 2);
        mpp.add("kin", 3);
        mpp.add("kin", 3);
        mpp.add("uts", 1);
        mpp.add("you", 1);
        mpp.add("jais", 2);
        mpp.add("sha", 2);
        mpp.add("ki", 3);
        mpp.add("ki", 3);
        mpp.add("ut", 1);
        mpp.add("yo", 1);
        mpp.add("jai", 2);
        mpp.add("sh", 2);
        mpp.add("k", 3);
        mpp.add("k", 3);

        System.out.println(mpp);

    }
}