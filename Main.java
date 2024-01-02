import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
Enter an initial string with each number and operand separated by a space " " (including the last one).
Press enter when desired function is typed, put an equals sign on that line to calculate
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static String userCalcString = "";
    static String userEquals;
    static char[] userCalcChars;
    static String[] userCalcStringArray;
    static ArrayList<String> userCalcArray = new ArrayList<>();
    static ArrayList<String> parenthesisArray = new ArrayList<>();
    static ArrayList<Double> numbers = new ArrayList<>();
    static ArrayList<String> operators = new ArrayList<>();
    static double answer=0;
    public static void printUserCalcArray(){
        for(String s : userCalcArray){
            System.out.print(s+" ");
        }
    }
    public static ArrayList<String> getUserCalcChars(){
        do{
            userCalcString = sc.nextLine();
            userEquals = sc.nextLine();
        }while(!userEquals.equals("="));
        userCalcString += userEquals;
        userCalcChars = userCalcString.toCharArray();

        userCalcStringArray = new String(userCalcChars).split(" ");
        userCalcArray.addAll(Arrays.asList(userCalcStringArray));

        return userCalcArray;
    }
    public static void parenthesis(ArrayList<String> calcArray){
        if (calcArray.contains("(")&&calcArray.contains(")")) {
        int pCount=0;
        for (int i=0;i<calcArray.size();i++){
            if(calcArray.get(i).equals("(")){
                pCount++;
            }
        }

        int pIterate=0;
        while(pIterate<pCount) {
            int start = 0, end = 0;
            for (int i = 0; i < calcArray.size(); i++) {
                if (calcArray.get(i).equals("(")) {
                    start = i;
                    calcArray.remove(i);
                }
                if (calcArray.get(i).equals(")")) {
                    end = i - 1;
                    calcArray.remove(i);
                    break;
                }
            }
            ArrayList<String> insideParenthesis = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                insideParenthesis.add(calcArray.get(i));
            }
            for (int i = start; i <= end; i++) {
                calcArray.remove(start);
            }
            evaluate(insideParenthesis);
            calcArray.add(start, String.valueOf(answer));
            pIterate++;
        }
        }
    }
    public static void evaluate(ArrayList<String> calcArray){
        if (calcArray.contains("*")||calcArray.contains("/")) {
            for(int i=0;i<calcArray.size();i++){
                String currentOp = calcArray.get(i);
                switch(calcArray.get(i)){
                    case "*","/"->{
                        operators.add(calcArray.get(i));
                        calcArray.remove(i);

                        numbers.add(Double.parseDouble(calcArray.get(i)));
                        double num2=Double.parseDouble(calcArray.get(i));
                        calcArray.remove(i);

                        numbers.add(Double.parseDouble(calcArray.get(i-1)));
                        double num1=Double.parseDouble(calcArray.get(i-1));
                        calcArray.remove(i-1);

                        if(currentOp.equals("*")) {
                            calcArray.add(i - 1, String.valueOf(num1 * num2));
                        }
                        if(currentOp.equals("/")) {
                            calcArray.add(i - 1, String.valueOf(num1 / num2));
                        }
                        if (calcArray.size()>1 && i<calcArray.size()) {
                            if (calcArray.get(i).equals("*")||calcArray.get(i).equals("/")) {
                                i--;
                            }
                        }
                    }
                }
            }
        }
        if (calcArray.contains("+")||calcArray.contains("-")){
            for(int i=0;i<calcArray.size();i++){
                String currentOp = calcArray.get(i);
                switch(calcArray.get(i)){
                    case "+","-"->{
                        operators.add(calcArray.get(i));
                        calcArray.remove(i);

                        numbers.add(Double.parseDouble(calcArray.get(i)));
                        double num2=Double.parseDouble(calcArray.get(i));
                        calcArray.remove(i);

                        numbers.add(Double.parseDouble(calcArray.get(i-1)));
                        double num1=Double.parseDouble(calcArray.get(i-1));
                        calcArray.remove(i-1);

                        if(currentOp.equals("+")) {
                            calcArray.add(i - 1, String.valueOf(num1 + num2));
                        }
                        if(currentOp.equals("-")) {
                            calcArray.add(i - 1, String.valueOf(num1 - num2));
                        }
                        if (calcArray.size()>1) {
                            if (calcArray.get(i).equals("+") || calcArray.get(i).equals("-")) {
                                i--;
                            }
                        }
                    }
                }
            }
        }
        answer=Double.parseDouble(calcArray.get(0));
    }
    public static void main(String[] args) {
        userCalcArray=getUserCalcChars();
        parenthesis(userCalcArray);
        evaluate(userCalcArray);

        System.out.println(answer);
        //cant do nested parenthesis!!!
        //
    }
}
