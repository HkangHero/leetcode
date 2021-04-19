package com.example.practise.basestructure_07;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:haokanghao
 * @date: 2021/4/19 10:01
 * @desc: 题目1：最大线段重复
 */
public class Code01_CoverMax {


    /**
     * 为什么是0.5? 如果是整数很容易将[1,2][2,3]算进去，如果是0.5那么 就不会出现这种情况
     * 暴力解法：将所有得线段进行排序，然后遍历，第一个和所有比较，找出最大值，依次第二个。。。
     * O(N*N)
     * @param lines
     * @return
     */
    private static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    /**
     * 使用堆来解决,O(NLogN)
     * 先排序，然后遍历
     * 1、查看堆中的最小值是否比现在要插入的值小，把小于插入的值全部踢出去
     * 2、将该值放入堆中，应该在根
     * 3、对比上次存在根的数，和此时根的数。
     * 为什么要小于最小值踢出去？因为是按照左区间的值开始定的，为什么不会出现[1,2] [2,3] 因为在while 条件中已经去掉了
     * @param m
     * @return
     */
    private static int maxCover2(int[][] m) {
     Line[] lines = new Line[m.length];
     //赋值
     for(int i =0;i<m.length;i++){
         lines[i] = new Line(m[i][0],m[i][1]);
     }
     //排序
     Arrays.sort(lines,new StartComparator());

     PriorityQueue<Integer> heap = new PriorityQueue<>();
     int max = 0;
     for(int i = 0; i< m.length; i++){
         //核心：堆不为空 并且 根节点<= 待插入值得开始
         while (!heap.isEmpty()&&heap.peek()<=lines[i].start){
             heap.poll();
         }
         heap.add(lines[i].end); // 插入得右区间
         max = Math.max(max,heap.size());
     }
        return max;
    }

    //线段实体类
    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }


    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }
    public static void main(String[] args) {
        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);
        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTime = 200000;
        for(int i =0;i<testTime;i++){

            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }

    }



    /**
     *
     * @param N 数组最长
     * @param L 最小数
     * @param R 最大数
     * @return
     */
    private static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans =  new int[size][2];
        for(int i =0;i<size;i++){
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if(a == b){
                b = a+1; //防止变成一个点
            }
            ans[i][0] = Math.min(a,b);
            ans[i][1] = Math.max(a,b);
        }
      return  ans;
    }


}
