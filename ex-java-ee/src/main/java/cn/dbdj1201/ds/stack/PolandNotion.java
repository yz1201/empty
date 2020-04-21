package cn.dbdj1201.ds.stack;

import java.util.Stack;

/**
 * @author tyz1201
 * @datetime 2020-04-20 15:41
 * 表达式计算器
 * 逆波兰计算器，后缀表达式计算
 * 34+5*6-  -》中缀表达式为正常的，3*4+5-6 前缀为*+-3456
 **/
public class PolandNotion {

    public static void main(String[] args) {
        PolandNotion notion = new PolandNotion();
        String expr = "3 4 + 5 * 6 -";
        System.out.println(notion.cal(expr));
    }

    private int cal(String expr) {
        String[] numStrs = expr.split(" ");
        Stack<String> numStack = new Stack<>();
        for (String item : numStrs) {
            if (item.matches("\\d+")) {
                numStack.push(item);
            } else {
                int num1 = Integer.parseInt(numStack.pop());
                int num2 = Integer.parseInt(numStack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                }
                numStack.push(res + "");
            }
        }
        return Integer.parseInt(numStack.pop());
    }


}
