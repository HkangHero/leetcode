package com.example.practise.basestructure_04;

/**
 * @author:haokanghao
 * @date: 2021/4/13 08:40
 * @desc: 求一个数组中逆序对的个数
 * arr[9,10,6,12,11,8]
 * 逆序对： 9,6  9,8  10,6  12,11 12,8
 *
 * 求解：假设：[9,6,10] [8,11,12]  都已经排好序
 *  l:左边数组的指针 (M) 左边是倒退
 *  r：右边数组的指针（R）右边也是倒退
 *
 *  如果arr[l]>arr[r] 符合 那么对于arr[l]就有 r-M 对
 *
 *  如果能排好序并且单调增或者减可以解决 可以用归并去解决
 *
 *  逆序对： 左大  右小
 *
 *
 */
public class ReversePair_Code3 {
    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }


    public static int process(int[] arr, int L, int R) {
        if(L == R){
            return 0;
        }
        int M = L+ ((R-L)>>1); //todo: 记住 运算符优先级
        return process(arr,L,M)+process(arr,M+1,R)+merge(arr,L,M,R);
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = help.length-1;
        int p1 = M;
        int p2 = R;
        int count = 0;

        while (p1>=L&&p2>M){
          count += arr[p1]>arr[p2]?(p2-M):0;
          help[i--] = arr[p1]>arr[p2]?arr[p1--]:arr[p2--];
        }
        while (p1>=L){
            help[i--] = arr[p1--];
        }
        while (p2>M){
            help[i--] = arr[p2--];
        }
        for(i =0 ;i<help.length;i++){
            arr[L+i] = help[i];
        }

        return  count;
    }


    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
