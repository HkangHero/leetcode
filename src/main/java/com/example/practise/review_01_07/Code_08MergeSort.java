package com.example.practise.review_01_07;



/**
 * @author:haokanghao
 * @date: 2021/4/21 16:18
 * @desc: 最小和问题 给定一个数组
 * arr[4,7,6,2,9]
 * 4：比4最小的和累加起来 0
 * 7：比7最小的累加起来 4
 * 6 比6最小的累加起来 4
 * 2：比2最小的累加起来  0
 * 9：比9左边最小的累加起来 4+7+6+2
 *
 * 求最后的和
 *
 *
 */
public class Code_08MergeSort {

    public static void sort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        divid(arr,0,arr.length-1);
    }


    private static int divid(int[] arr, int L, int R) {
        if(L == R){
            return 0;
        }
        int mid = L+ ((R -L)>>1);
       return  divid(arr,L,mid)+divid(arr,mid+1,R)+merge(arr,L,mid,R);
    }

    /**
     * 一组数 按找左边的支点进行比较  两份数据都是排好序的
     * [2.4.6]   [3,6,8]
     * 如果左边比右边的小 那么右边的指针后全部都比左边大
     *
     */
    private static int merge(int[] arr, int L, int M, int R) {
        int[] help =  new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M+1;
        int count = 0;
        // 一边计算 一边排序 不影响
        while (left<=M&&right<=R){

            count+= arr[left] < arr[right]?(R - right + 1) * arr[left]:0;

            // todo: 注意 这里不能等于，
            help[index++] = arr[left]<arr[right]?arr[left++]:arr[right++];

        }

        while (left <= M) {
            help[index++] = arr[left++];
        }

        while (right <= R) {
            help[index++] = arr[right++];
        }

        for (index = 0; index < help.length; index++) {
            arr[L + index] = help[index];
        }
        return count;


    }

}
