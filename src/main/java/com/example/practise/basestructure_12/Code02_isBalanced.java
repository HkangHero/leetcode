package com.example.practise.basestructure_12;

/**
 * @author:haokanghao
 * @date: 2021/5/20 16:45
 * @desc: 判断二叉树是否是一颗平衡树
 *     1、X左树得是搜索二叉树
 *     2、X右树得是搜索二叉树
 *     3、X左树Max < x
 *     4、X右树得Min > x
 */
public class Code02_isBalanced {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }
    // 构造信息
    public static class Info{
        public boolean isBalanced;
        public int height;
        public Info (boolean i, int h){
            this.isBalanced = i;
            this.height = h;
        }
    }


    public static boolean isBalanced(Node head){
        return proecess(head).isBalanced;
    }



    public static  Info proecess(Node node){
        if(node == null){
            return new Info(true,0);
        }
        Info left = proecess(node.left);
        Info right = proecess(node.right);
        int hight = Math.max(left.height,right.height);
        boolean isBalanced = true;
        if(!left.isBalanced){
            isBalanced = false;
        }
        if(!right.isBalanced){
            isBalanced = false;
        }
        if(Math.abs(left.height - right.height)>1){
            isBalanced = false;
        }
        return new Info(isBalanced,hight);
    }



}
