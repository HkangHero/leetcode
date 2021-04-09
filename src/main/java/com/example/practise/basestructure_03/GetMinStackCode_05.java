package com.example.practise.basestructure_03;

import java.util.*;

/**
 * @author:haokanghao
 * @date: 2021/4/9 14:29
 * @desc: 实现一个特殊的栈、在基本功能的基础上，再实现返回栈中最小元素的功能
 * 两个栈，一个存放元素 一个存放最小值
 * pop、push、getMin操作的时间复杂度都是O(1)
 */
public class GetMinStackCode_05 {

    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        private int minSize = 0;
        public MyStack1() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            stackData.push(newNum);
            if(minSize==0){
                stackMin.push(newNum);
            }else{
                Integer peek = stackMin.peek();
                if( peek>newNum){
                    stackMin.push(newNum);
                }else{
                    stackMin.push(peek);
                }
            }
            minSize++;
        }


        public int pop(){
            if(minSize == 0){
                throw new RuntimeException("栈空了");
            }
            Integer pop = stackData.pop();
            stackMin.pop();
            minSize--;
            return pop;
        }

        public int getMin(){
            if(minSize == 0){
                throw new RuntimeException("栈空了");
            }
            return stackMin.peek();
        }
    }

    /**
     * 检测
     * @param set
     * @param myStack11
     * @return
     * 先从栈回去到最小值，在和tree中进行对比
     * 如果不相同 推出
     * 想过相同   从存放元素出栈，然后将这个元素从treeset移除 不一定是最小值 再次对比 直到treeset 没有值
     */
    private static Boolean checkResult(TreeSet<Integer> set, MyStack1 myStack11) {
        boolean flag = false;
        while (true){
            if(set.size() == 0){
                break;
            }
            int min = myStack11.getMin();
            if(!set.first().equals(min)){
                flag = false;
                break;
            }
            flag = true;
            int pop = myStack11.pop();
            set.remove(pop);
        }
        return flag;
    }


    public static void main(String[] args) {
        int oneTestDataNum = 10;
        int value = 10000;
        int testTimes = 100;
        MyStack1 myStack1 = new MyStack1();
        for(int i =0;i<testTimes;i++){
            TreeSet<Integer> set = new TreeSet<>();

            for(int j = 0; j<oneTestDataNum;j++){
                int nums = 0;
                do{
                  nums = (int) (Math.random() * value);
                }while (!set.add(nums)) ;
                myStack1.push(nums);
            }
            if(!checkResult(set,myStack1)){
                System.out.println("error");
            }

        }
    }



}
