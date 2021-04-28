package com.example.practise.basestructure_10;

/**
 * @author:haokanghao
 * @date: 2021/4/28 13:51
 * @desc: 二叉树遍历递归
 */
public class Code01_TreeRrcursive {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int v) {
            value = v;
        }
    }
   //先序遍历
    public static void pre(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }
}
