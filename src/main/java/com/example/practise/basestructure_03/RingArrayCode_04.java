package com.example.practise.basestructure_03;

/**
 * @author:haokanghao
 * @date: 2021/4/9 14:15
 * @desc: 数组实现单向队列（循环数组）
 *
 */
public class RingArrayCode_04 {


    public static class MyQueue {
        private int[] arr;
        private int pushi;// end  尾指针
        private int polli;// begin 头指针
        private int size;//  数量
        private final int limit; // 数组长度


        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if(size+1>limit){
                throw  new RuntimeException("队列撑爆了");
            }
            arr[polli] = value;
            polli =  pointMove(polli);
            size++;
        }

        public int pop(){
            if (size == 0) {
                throw new RuntimeException("队列空了");
            }
            int num = arr[pushi];
            pushi = pointMove(pushi);
            size--;
            return  num ;
        }


        //指针下移
        private int pointMove(int polli) {
           return polli>=limit-1?0:polli+1;
        }
    }


    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(10);
        for(int i =0;i<10;i++){
            myQueue.push(i);
        }
        for (int i = 0 ;i<10;i++){
            System.out.println(myQueue.pop());
        }
    }
}
