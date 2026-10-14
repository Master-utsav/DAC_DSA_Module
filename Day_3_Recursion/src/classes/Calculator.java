package classes;

public class Calculator {

    public static int getRemainderOfTwoNumbers(int a, int b){
        return unsignedGetRemainderOfTwoNumbers(Math.abs(a), Math.abs(b));
    }
    private static int unsignedGetRemainderOfTwoNumbers(int a, int b){
        if(b > a) return a;
        else return getRemainderOfTwoNumbers(b - a, b);
    }

    public static int multiplyTwoNumber(int a, int b){
        int multiply = unsignedMultiplyTwoNumber(Math.abs(a), Math.abs(b));
        if((a < 0 && b < 0) || (a > 0 && b > 0)) return multiply;
        else return -multiply;
    }
    private static int unsignedMultiplyTwoNumber(int a, int b){
        if(a == 1) return b;
        if(b == 1) return a;
        if(a == 0 || b == 0) return 0;

        if(a <= b) return b + multiplyTwoNumber(a - 1, b);
        else return a + multiplyTwoNumber(a , b - 1);
    }

    public static int divideTwoNumbers(int a, int b){
        if (b == 0) {
            throw new ArithmeticException("/ by zero");
        }
        if(Math.abs(a) < Math.abs(b)) return 0;
        if(Math.abs(a) == Math.abs(b)) return 1;

        int divide = unsignedDivideTwoNumbers(Math.abs(a), Math.abs(b));
        if((a < 0 && b < 0) || (a > 0 && b > 0)) return divide;
        else return -divide;
    }
    private static int unsignedDivideTwoNumbers(int a, int b){
        if(a < b) return 0;
        return 1 + unsignedDivideTwoNumbers(a - b, b);
    }



}
