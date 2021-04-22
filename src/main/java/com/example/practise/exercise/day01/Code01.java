package com.example.practise.exercise.day01;

/**
 * @author:haokanghao
 * @date: 2021/4/22 08:58
 * @desc:
 *  给定一个有序数组arr，代表坐落在X轴上得点， 给点一个正数K，代表绳子的长度 返回绳子最多压几个点？
 *  即使绳子端点处盖住的点也算
 */
public class Code01 {
    /**
     *  第一个：使用贪心算法
     *  绳子的末端必经过绳子的某个端点
     *  从第一个开始作为绳子的端点
     * @param arr
     */
    public static int function1(int[] arr,int steep){
        if(arr == null || arr.length < 2){
            return -1;
        }
        int max = 0;
        for(int i = 0; i<arr.length;i++){
            max =  Math.max(i-selectLong(arr,arr[i]-steep)+1,max);
        }
        return max;

    }
  //
    private static int selectLong(int[] arr, int M) {
        int L =0;
        int R = arr.length-1;
        int cout = -1;
        while (L<=R){
            int mid = (L+R)/2;
            if(arr[mid]>=M){
                cout = mid;
                R = mid-1;
            }else {
                L = mid+1;
            }
        }
        return  cout;

    }

    public static int digui(int[] arr ,int L,int R,int taget){
        if(L == R){
            return arr[R]<taget?R+1:R;
        }
        int mid = L+ ((R-L)>>1);
        if(arr[mid]>=taget){
            return   digui(arr,L,mid-1,taget);
        }else{
            return  digui(arr,mid+1,R,taget);
        }
    }

    //滑动窗口
    public static int function2(int[] arr,int target){
        if(arr == null || arr.length < 2){
            return -1;
        }
        int max = 0;
        int R = 0;
        int L = 0;
        int N =arr.length;
        for(int i =0;i<arr.length;i++){
            while (R<N&&arr[R]-arr[i]<=target){
                R++;
            }
            max = Math.max(max,(R-L++));
        }
       return max;
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,5,8, 10,11,12,13,14,15,17,20,22,26,28,33};
        System.out.println(   function1(arr,5));
        System.out.println(   function2(arr,5));

        System.out.println(  digui(arr,0,arr.length-1,9));

    }






}
