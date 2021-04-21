package com.example.practise.review_01_07;

/**
 * @author:haokanghao
 * @date: 2021/4/21 14:19
 * @desc: 数组实现单向队列（循环数组）
 * 1、一个数组，用两个指针，一前一后，一个变量总数来控制临界
 */
public class Code_03RingArray {
    public static class MyQueue {
        int[] arr ;
        int pre = 0;
        int next = 0;
        final int limit;
        int size = 0;


        public MyQueue(int size){
            arr = new int[size];
            limit = size;
        }
        //新增
        public void add(int value){
            if(size == limit){
                throw  new RuntimeException("数组已经存满");
            }
            arr[next] = value;
            next = moveIndex(next);
            size++;
        }
        //弹出
        public int pop(){
            if(size == 0){
                throw  new RuntimeException("数组已空");
            }
           int value = arr[pre];
            pre = moveIndex(pre);
            size -- ;
            return value;
        }

        public int moveIndex(int index){
           return  index>=limit-1?0:index+1;
        }

    }

}
