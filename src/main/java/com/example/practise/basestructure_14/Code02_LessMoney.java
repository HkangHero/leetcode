package com.example.practise.basestructure_14;

import java.util.PriorityQueue;

/**
 * @author:haokanghao
 * @date: 2021/5/22 10:46
 * @desc: 分金问题
 */
public class Code02_LessMoney {
    public static int lessMoney2(int[] arr){
        //1、先建一个小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i =0;i<arr.length;i++){
            priorityQueue.add(arr[i]);
        }
        //2、
        int sum =0;
        int cur = 0;
        while (priorityQueue.size()>1){
            cur = priorityQueue.poll() + priorityQueue.peek();
            sum+=cur;
            priorityQueue.add(cur);
        }
        return sum;
    }
}
