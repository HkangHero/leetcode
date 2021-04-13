package com.example.practise.basestructure_04;

/**
 * @author:haokanghao
 * @date: 2021/4/13 10:20
 * @desc:  number 右边有多少数*2 后依然小于numer 的个数
 */
public class BiggerThanRightTwiceCode_4 {
    // 排序
    public static int biggerTwice(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int L ,int R){
        if(L == R){
            return  0;
        }
        int M = L+ ((R-L)>>1);
        return process(arr,L,M)+process(arr,M+1,R)+merge(arr,L,M,R);
    }

    //1、左边的数组为基组 先计算出有多少数符合，
    //2、这样只需要走一遍就可以了
    //3、最后再将两个有序数组排序
    public static int merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];

        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - M - 1;
        }

        int i =0;
        int p1= L;
        int p2 = M+1;

        while (p1<=M&&p2<=R){
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=M){
            help[i++] = arr[p1++];
        }
        while (p2<=R){
            help[i++] = arr[p2++];
        }
        for(i = 0; i<help.length;i++){
           arr[L+i] = help[i];
        }
      return  ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
