package com.example.practise.review_01_07;

import java.util.Arrays;

/**
 * @author:haokanghao
 * @date: 2021/4/21 15:08
 * @desc: todo： 归并排序
 */
public class Code_07MergeSort {

    //todo:采用递归方法
    public static void sort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        divid(arr,0,arr.length-1);
    }

    public static void divid(int[] arr ,int L,int R){
        if(L == R){
            return;
        }
        int mid = L + ((R-L)>>1);
        divid(arr,L,mid);
        divid(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    // 归并排序
    private static void merge(int[] arr, int L, int M, int R) {
        int[] share = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M+1;
        while (left<=M&&right<=R){
           share[index++] = arr[left]>arr[right]?arr[left++]:arr[right++];
        }
        while (left<=M){
            share[index++] = arr[left++];
        }
        while (right<=M){
            share[index++] = arr[right++];
        }
        for(int i =0;i<share.length;i++){
            arr[L+i] = share[i];
        }
        Arrays.sort(share);
    }


    //todo: 采用非递归堆排序
    public static void sort2(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N){
            int L = 0;
            // 内循环
            while (L< N){
                int M = L+mergeSize -1; //左边最后一位
                if(M < N){
                    break;
                }
                // M+mergeSize 不需要加1 N-1是总数
                int R = Math.min(M+mergeSize,N-1);
                //划分好区域 开始merge
                merge(arr,L,M,R);
                L = R+1;
            }
            //走完L 要判断 下次步长是否会超
            if(N/2 < mergeSize){
                break;
            }
            //调整步长
            mergeSize <<= 1;
        }
    }



}
