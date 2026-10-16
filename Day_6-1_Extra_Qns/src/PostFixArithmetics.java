import java.util.List;
import java.util.Stack;

public class PostFixArithmetics {

    /*
    2. Eveluate postfix expression (using stack (create own stack))
    Example 1:
    Input: ["2","1","+","3","*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
    Example 2:

    Input: = ["4","13","5","/","+"]
    Output: 6
    Explanation: (4 + (13 / 5)) = 6
    Example 3:

    Input:= ["10","6","9","3","+","-11","*","/","","17","+","5","+"]
    Output: 22
    Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
            = ((10 * (6 / (12 * -11))) + 17) + 5
            = ((10 * (6 / -132)) + 17) + 5
            = ((10 * 0) + 17) + 5
            = (0 + 17) + 5
            = 17 + 5
            = 22
    */
    private static boolean isValidNumber(String obj){
        try{
            Integer.parseInt(obj);
            return true;
        }
        catch (Exception ignore){
            return false;
        }
    }
    public static int calculate(List<String> expression){
        Stack<Integer> st = new Stack<>();
        if(expression.isEmpty()) return 0;
        for(int i = 0; i < expression.size(); i++){
            if(isValidNumber(expression.get(i))){
                st.push(Integer.parseInt(expression.get(i)));
            }
            else{
                if(st.isEmpty() || st.size() == 1) return 0;
                else{
                    String operation = expression.get(i);
                    int operand1 = st.pop();
                    int operand2 = st.pop();
                    if(operation.equals("+")){
                        st.push(operand2 + operand1);
                    }
                    else if(operation.equals("-")){
                        st.push(operand2 - operand1);
                    }
                    else if(operation.equals("*")){
                        st.push(operand2 * operand1);
                    }
                    else if(operation.equals("/")){
                        if(operand2 == 0){
                            System.out.println("Dividing zero exception");
                            return 0;
                        }
                        st.push(operand2 / operand1);
                    }
                    else{
                        System.out.println("Not a valid expression");
                        return 0;
                    }
                }
            }
        }
        if(st.size() != 1) {
            System.out.println("Not a valid expression");
            return 0;
        }
        else {
            int top  = st.pop();
            System.out.println("Experssion " + expression + " results to " + top);
            return top;
        }

    }

}
