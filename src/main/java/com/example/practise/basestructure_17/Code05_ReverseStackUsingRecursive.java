package com.example.practise.basestructure_17;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author:haokanghao
 * @date: 2021/6/1 10:02
 * @desc: 将栈得数据倒过来，不使用额外得空间
 */
public class Code05_ReverseStackUsingRecursive {


    public static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()){
            return;
        }else{
            int f = f(stack);
            reverse(stack);
            stack.push(f);
        }
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
