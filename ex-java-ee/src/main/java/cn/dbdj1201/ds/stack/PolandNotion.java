package cn.dbdj1201.ds.stack;

import java.util.ArrayList;
import java.util.List;
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
//        String expr = "3 4 + 5 * 6 -";
//        System.out.println(notion.cal(expr));
        List<String> stringList = toInfixExprList("1+((2+3)*4)*5");
        System.out.println(stringList);
        System.out.println("--->");
        System.out.println(notion.transferToSuffixExprList(stringList));
        System.out.println(notion.cal("1 2 3 + 4 * 5 * +"));
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

    public static List<String> toInfixExprList(String infix) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        StringBuilder str;
        char c;
        do {
            if ((c = infix.charAt(i)) < 48 || (c = infix.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = new StringBuilder();
                while (i < infix.length() && (c = infix.charAt(i)) >= 48 && (c = infix.charAt(i)) <= 57) {
                    str.append(c);
                    i++;
                }
                ls.add(str.toString());
            }
        } while (i < infix.length());
        return ls;
    }

    /**
     * 中缀表达式转后缀
     * 转化为list，比较好操作
     *
     * @param infixList
     * @return
     */
    public List<String> transferToSuffixExprList(List<String> infixList) {
        Stack<String> operStack = new Stack<>();
        List<String> res = new ArrayList<>();
        infixList.forEach(str -> {
            if (str.matches("\\d+")) {
                res.add(str);
            } else if (str.equals("(")) {
                operStack.push(str);
            } else if (str.equals(")")) {
                while (!operStack.peek().equals("(")) {
                    res.add(operStack.pop());
                }
                operStack.pop();//丢弃一对括号。
            } else {
                while (!operStack.isEmpty() && (getPriority(operStack.peek()) >= getPriority(str))) {
                    res.add(operStack.pop());
                }
                operStack.push(str);
            }
        });
        while (!operStack.isEmpty())
            res.add(operStack.pop());
        return res;
    }


    //优先级
    //返回运算符的优先级+-*/

    /**
     * + - 0
     * * / 1
     * 其他-1
     *
     * @param str
     */
    public static int getPriority(String str) {
        if (str.equals("+") || str.equals("-"))
            return 0;
        if (str.equals("*") || str.equals("/"))
            return 1;
        return -1;
    }


}
