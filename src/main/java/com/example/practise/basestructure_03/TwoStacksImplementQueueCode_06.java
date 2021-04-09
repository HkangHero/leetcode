package com.example.practise.basestructure_03;

import java.util.Stack;

/**
 * @author:haokanghao
 * @date: 2021/4/9 15:26
 * @desc: 用栈实现队列
 * 两个栈：push栈  pop栈 来回倒
 */
public class TwoStacksImplementQueueCode_06 {

    public static class TwoStacksQueue {

        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue(){
            this.stackPop = new Stack<>();
            this.stackPush = new Stack<>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

       public void add(int pushInt) {
           stackPush.push(pushInt);
           pushToPop();
       }

        public int poll() {
            if(stackPop.empty()){
               throw new RuntimeException("空");
            }
            Integer pop = stackPop.pop();
            pushToPop();
            return pop;
        }
        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }

    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
