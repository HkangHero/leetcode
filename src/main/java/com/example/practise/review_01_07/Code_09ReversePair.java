package com.example.practise.review_01_07;

/**
 * @author:haokanghao
 * @date: 2021/4/21 17:11
 * @desc: 求一个数组中逆序对的个数
 * arr[9,10,6,12,11,8]
 * 逆序对： 9,6  9,8  10,6  12,11 12,8
 *
 * 逆序对是左大 右小
 *
 * 在排序得时候 应该是左大到小  这样可以确定窗口
 * [4,6,9]  [3,8,12]
 *  刚开始应该在 9 与 12 对比 谁大 先放入到help 数组最后得位置
 *   不要从小到大  判断条件不好成立
 *   9 < 8 =》 2个
 *
 */
public class Code_09ReversePair {
    public static  void  sort(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        process(arr,0,arr.length-1);
    }


    public static  int process(int[] arr,int L,int R){

        if(L == R){
            return 0;
        }

        int M = L+( (R-L)>>1);

        return process(arr,L,M)+process(arr,M+1,R)+merge(arr,L,M,R);
    }

    public static  int merge(int[] arr ,int L,int M,int R){
        int[] help = new int[R-L+1];
        int index = R-L;
        int  left = M;
        int  right = R;
        int count = 0;
        while (left>=L &&right>M){
            count += arr[left]>arr[right]?(right-M):0;
            help[index--] = arr[left]>arr[right]?arr[left--]:arr[right--];
        }

        while (left>=L ){
            help[index--] = arr[left--];
        }
        while (right>=L ){
            help[index--] = arr[right--];
        }
        for(index = 0; index<help.length;index++){
            arr[L+index] =  help[index];
        }
        return  count;
    }
}
