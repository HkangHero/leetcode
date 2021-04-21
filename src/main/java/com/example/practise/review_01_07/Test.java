package com.example.practise.review_01_07;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1,3,5,6,7,9,10};
        int steep = 3;
        int count = 0;
        for(int i =0;i<arr.length;i++){
            count = Math.max(check(arr,arr[i]-steep),count);
        }
        System.out.println( count);
    }

    // 求某个数在某个数组的小于的最小位置
    private static int check(int[] arr, int M) {
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




}
