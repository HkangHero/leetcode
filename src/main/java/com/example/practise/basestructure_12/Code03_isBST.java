package com.example.practise.basestructure_12;

/**
 * @author:haokanghao
 * @date: 2021/5/20 18:13
 * @desc: 判断是否是搜索二叉树
 */
public class Code03_isBST {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info{
        public boolean isBST; //是否是二叉树
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node node ){
        if( node == null){
            return false;
        }

         return  process(node).isBST;
    }


    //递归
    public  static  Info process(Node x){
        // 不好设置 就在上游
        if(x == null){
            return  null;
        }
        Info left = process(x.left);
        Info right = process(x.right);
        //todo: 将判空留给下面
        int max = x.value; // 假设节点得最大值是自己
        if(left!=null){
            max  =Math.max(max,left.max);
        }

        if(right!=null){
            max  =Math.max(max,right.max);
        }
        int min = x.value; // 假设节点最小值是自己

        if(left!=null){
            min  =Math.min(min,left.min);
        }
        if(right!=null){
            min  =Math.min(min,right.min);
        }
        boolean isBST = true;
        //列举违反条件
        if(left!=null&& left.isBST == false){
            isBST = false;
        }

        if(right!=null&& right.isBST == false){
            isBST = false;
        }

        if(left!=null && left.max >= x.value){
            isBST = false;
        }
        if(right!=null && right.min <= x.value){
            isBST = false;
        }
            return new Info(isBST,min,max);

    }






}
