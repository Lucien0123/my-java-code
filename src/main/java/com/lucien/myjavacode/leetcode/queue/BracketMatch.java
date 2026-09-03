package com.lucien.myjavacode.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author huoershuai
 * @date 2026/09/03
 */
public class BracketMatch {

    /**
     * 括号匹配规则，当发现右括号的时候，找栈顶元素是否是对应的左括号
     */
    private static final Map<Character, Character> MATCH_RULE = new HashMap<Character, Character>(){{
        put(')', '(');
        put('}', '{');
        put(']', '[');
    }};

    public static void main(String[] args) {
        String str = "()[]{}";
        String str1 = "([)]";
        String str2 = "([])";
        System.out.println(match(str));
        System.out.println(match(str1));
        System.out.println(match(str2));
    }

    private static boolean match(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : str.toCharArray()) {
            if (!MATCH_RULE.containsKey(c)) {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (!Objects.equals(stack.peek(), MATCH_RULE.get(c))) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }
}
