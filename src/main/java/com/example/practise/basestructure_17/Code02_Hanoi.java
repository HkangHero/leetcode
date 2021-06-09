package com.example.practise.basestructure_17;

/**
 * @author:haokanghao
 * @date: 2021/6/1 09:25
 * @desc: 汉诺塔问题
 */
public class Code02_Hanoi {

    public static void hanoi2(int n){
        if(n>0){
            func(n,"left","right","mid");
        }
    }

    private static void func(int n, String from, String to, String other) {
        if(n == 1){
            System.out.println("Move 1 from " + from + " to " + to);
        }else{
            func(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            func(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi2(3);
    }
}
