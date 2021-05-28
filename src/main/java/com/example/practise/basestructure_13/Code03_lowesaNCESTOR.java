package com.example.practise.basestructure_13;

/**
 * @author:haokanghao
 * @date: 2021/5/21 16:58
 * @desc:给定一颗二叉树得头节点head，和另外两个节点a和b。返回a和b的最低公共祖先
 *    最低公共祖先：两个节点在祖先第一次节点相遇
 */
public class Code03_lowesaNCESTOR {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info{
        public boolean findA;
        public boolean findB;
        public Node node;

        public Info(boolean findA, boolean findB, Node node) {
            this.findA = findA;
            this.findB = findB;
            this.node = node;
        }
    }

    public static Info process(Node x,Node a,Node b){
        if(x == null){
            return  new Info(false,false,null);
        }
        Info left = process(x.left,a,b);
        Info right = process(x.right,a,b);
         boolean findA = left.findA || right.findA || x == a;
         boolean findB = left.findB || right.findB || x == b;
         Node ans = null;
         if(left.node != null){
             ans = left.node;
         }else if(right.node != null){
             ans = right.node;
        }else {
             if(findA && findB){
                 ans = x;
             }
         }
         return  new Info(findA,findB,ans);
    }


}
