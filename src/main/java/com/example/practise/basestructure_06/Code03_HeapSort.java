package com.example.practise.basestructure_06;

import lombok.Data;

/**
 * @author:haokanghao
 * @date: 2021/4/16 16:53
 * @desc: 最小堆的排序
 */
public class Code03_HeapSort {
    private  static void swap(int[] arr, int L, int R) {
        int mid = arr[L];
        arr[L] =  arr[R];
        arr[R] = mid;
    }

    // 最小堆排序
    public  void sort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        // O(N*logN)
//		for (int i = 0; i < arr.length; i++) { // O(N)
//			heapInsert(arr, i); // O(logN)
//		}
        // O(N) 从底层向上走 每个数只会执行N次，越来越少
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }


        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // O(N*logN)
        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }
    }

    // arr[index]刚来的数，往上
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // arr[index]位置的数，能否往下移动
    public  void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大的孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }



}
