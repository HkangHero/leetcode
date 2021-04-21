package com.example.practise.review_01_07;

import java.util.Stack;

/**
 * @author:haokanghao
 * @date: 2021/4/21 14:42
 * @desc: todo: 实现一个栈，再原有的功能，增加一个功能弹出最小的值
 * 做法：两个栈
 */
public class Code_04GetMinStack {
    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        private int minSize = 0;
        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        // 加入
        public void push(int newNum) {
            stackData.push(newNum);
            if(stackMin.size() == 0){
                stackMin.push(newNum);
            }else{
                Integer peek = stackMin.peek();
                if(peek>newNum){
                    peek = newNum;
                }
                stackMin.push(peek);
            }
        }

        public int pop(){
            if(stackData.size() ==0){
                throw  new RuntimeException("栈已空");
            }
            stackMin.pop();
            Integer pop =  stackData.pop();
            return pop;
        }

        public int getMin(){
            if(stackMin.size() == 0){
                throw  new RuntimeException("栈已空");
            }
            return stackMin.peek();
        }


    }
}
