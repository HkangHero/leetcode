package com.example.practise.exercise.day01;

/**
 * @author:haokanghao
 * @date: 2021/4/22 10:37
 * @desc:
 */
public class Code03 {
    public static void main(String[] args) {
        int n =-1;

        n--; // -- 是为了不需要做判断这个数是否就是2的倍数 如果是就是自己 不是走下面的逻辑 减1的话就不需要判断了
        n |= n>>1;
        n |= n>>2;
        n |= n>>4;
        n |= n>>8;
        n |= n>>16;
        n+=1;
       int num =  n == 0 ?1:n; //如果是负数 最后结果都是0 需要做一下判断
       System.out.println(num);
    }
}
