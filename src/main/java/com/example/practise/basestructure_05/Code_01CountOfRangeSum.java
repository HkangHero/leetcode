package com.example.practise.basestructure_05;

/**
 * @author:haokanghao
 * @date: 2021/4/13 14:14
 * @desc: leetcode  https://leetcode-cn.com/problems/count-of-range-sum/
 *
 * 以后可以用有序表
 * [i,j] 转变成 [0,j]  - [0,i]
 * 问题转换成 以i结尾的前缀和
 * arr[0.....17] 整体前缀和100，求在[60,90]
 * 求[0...16] 前缀和为70   求在[60,90]范围 转变成 [ 0....16] 前缀和在 [30,60],因为arr[17] = 30
 *  sum[]保存的是前缀和数组，sum[i] 代表的 i 之前有多少个落在 [arr[i] - up ,arr[i]-low]上
 *
 *
 *  eg: [3,4,8,10]  [5,7,10,12]   [-1,1]
 *  =>右数组： 5 : [4,6] 在左边的数有几个落在这个上面
 *             7:  [6,8]在左边的数有几个落在这个上面
 *             因为排好序的 所以符合单调性
 */
public class Code_01CountOfRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null|| nums.length == 0){
            return 0;
        }
        long[] sum = new long[nums.length]; //  第i个位置   代表以i结尾的字串符合在【lower,upper】范围

        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i]; // 第i个位置 = 第i-1个位置 + 当前位置
        }

        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public int process(long[] sum, int L, int R, int lower, int upper) {
        if(L == R){ // 如果相等,代表原始数0-L前缀和是9 basecase
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L+ ((R-L)>>1);
      return   process(sum,L,M,lower,upper)+ process(sum,M+1,R,lower,upper)+ merge(sum, L, M, R, lower, upper);
    }

    public int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int windowL = L; // 左数组低水位
        int windowR = L; // 左数组高水位
        int ans = 0 ;
        // [windowL, windowR)
        for( int j = M+1; j <=R;j++){
            long min = arr[j ] - upper;
            long max = arr[j ] - lower;
           // 例如：要找的窗口范围是是 [2,-1]
            while (windowR <= M && arr[windowR] <= max){ // 小于等于2
                windowR++;
            }
            while (windowL<=M&&arr[windowL]<min){ //-1 前一个数 ，形成一个窗口
                windowL++;
            }
          ans += windowR - windowL; // 上面形成窗口不需要+1
        }

        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        while (p1<=M&&p2<=R){
            help[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=M){
            help[i++] = arr[p1++];
        }
        while (p2<=R){
            help[i++] = arr[p2++];
        }

        for(i =0;i<help.length;i++){
            arr[L+i] = help[i];
        }

        return  ans;

    }

}
