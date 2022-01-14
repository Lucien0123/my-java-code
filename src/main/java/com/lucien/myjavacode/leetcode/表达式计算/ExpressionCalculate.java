package com.lucien.myjavacode.leetcode.表达式计算;

/**
 * @author huoershuai
 * Created on 2022-01-14
 */
public class ExpressionCalculate {

    public static void main(String[] args) {
        String a = "1*2-3/4+5*6-7*8+9/10";
        System.out.println(calculate(a));
    }

    public static int calculate(String s) {
        s += " ";
        int n = s.length();
        String number = "";   // 临时变量用于组装数字
        String[] ops = new String[(n+1)/2];   // 操作符栈
        int opsIndex = -1;     // 操作符栈的下标
        String[] tokens = new String[n-1];    // 后缀表达式结果, -1是因为多了空格
        int tokensIndex = -1;
        for(int i = 0; i < n; i++) {

            if (Character.isDigit(s.charAt(i))) {
                number += s.charAt(i);
                continue;
            } else {
                if (!"".equals(number)) {
                    tokensIndex++;
                    tokens[tokensIndex] = number;  // 将数字补充到后缀表达式中
                    number = "";
                }
            }
            if (Character.isSpaceChar(s.charAt(i))) {
                continue;
            }
            while (opsIndex >= 0 && getOpsRank(ops[opsIndex]) >= getOpsRank(Character.toString(s.charAt(i)))) {
                // 如果栈顶操作符优先级 大于等于 当前操作符号，则将操作符写入token
                tokensIndex++;
                tokens[tokensIndex] = ops[opsIndex];
                opsIndex--;
            }
            opsIndex++;
            ops[opsIndex] = Character.toString(s.charAt(i));

        }
        while(opsIndex >= 0) {
            tokensIndex++;
            tokens[tokensIndex] = ops[opsIndex];
            opsIndex--;
        }
        for (int j = 0; j <= tokensIndex; j++) {
            System.out.println(tokens[j]);
        }
        return evalRPN(tokens, tokensIndex);
    }

    public static int evalRPN(String[] tokens, int maxIndex) {
        int[] numbers = new int[maxIndex+1];
        int index = -1;
        for (int i = 0; i <= maxIndex; i++) {
            String token = tokens[i];
            switch(token) {
                case "+" :
                    index--;
                    numbers[index] = numbers[index] + numbers[index + 1];
                    break;
                case "-" :
                    index--;
                    numbers[index] = numbers[index] - numbers[index + 1];
                    break;
                case "*" :
                    index--;
                    numbers[index] = numbers[index] * numbers[index + 1];
                    break;
                case "/" :
                    index--;
                    numbers[index] = numbers[index] / numbers[index + 1];
                    break;
                default :
                    index++;
                    numbers[index] = Integer.parseInt(token);
                    break;
            }
        }
        return numbers[index];
    }


    public static int getOpsRank(String ops) {
        if ("*".equals(ops) || "/".equals(ops)) {
            return 2;
        }
        if ("+".equals(ops) || "-".equals(ops)) {
            return 1;
        }
        return 0;
    }
}
