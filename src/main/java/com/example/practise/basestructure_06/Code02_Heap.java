package com.example.practise.basestructure_06;

import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:haokanghao
 * @date: 2021/4/16 15:40
 * @desc: 小堆的加入 和弹出最大值
 */
public class Code02_Heap {


    @Data
    static
    class MyMaxHeap{
        private int[] heap;
        private final int limit ;
        private int heapSize;

        public MyMaxHeap(int limit){
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }
        public int[] getHeap(){
            return heap;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return  heapSize == limit;
        }

        public void push(int value){
            if(heapSize == limit){
                throw  new RuntimeException("已满");
            }
            heap[heapSize] = value;
            heapInsert(heap,heapSize++);
        }

        //把最大值返回
        public int pop(){
            if(heapSize == 0){
                throw  new RuntimeException("已空");
            }
            int result =  heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return  result;
        }

        //插入
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
        //
        private void heapify(int[] arr,int index,int size){
            // 先防止越界
            while (index*2+1<size){
                // 先找出 左子树 和 右子树那个最大
              int mid = index*2+2<size&&arr[index*2+2]>arr[index*2+1]?index*2+2:index*2+1;
              //找出在和节点进行比较
              mid = arr[index] >= arr[mid]?index:mid;

              if(mid == index){
                  return;
              }
              swap(arr,index,mid);
              index = mid;
            }
        }

        private void swap(int[] arr, int L, int R) {
            int mid = arr[L];
            arr[L] =  arr[R];
            arr[R] = mid;
        }

    }
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }







        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
