package com.example.practise.basestructure_12;

/**
 * @author:haokanghao
 * @date: 2021/5/20 18:51
 * @desc:给定一颗二叉树得头节点head，任何两个节点之间都存在距离，返回整颗二叉树得最大距离，每个节点只经过一次
 */
public class Code04_MaxDistance {
    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int dis, int h){
            this.maxDistance = dis;
            this.height = h;
        }
    }
    public static  int maxDistance(Node node){
        if( node == null){
            return 0;
        }
       return process(node).maxDistance;
    }


    public static Info process(Node node){
        if(node == null){
            return new Info(0,0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        // 最大高度 + 自己
        int height = Math.max(left.height,right.height) + 1;
        //todo:  比如左子树最大距离（它自己得最左节点到右节点5），右子树最大距离，左高度+右高度+1 三者那个最大
        //todo: 最大距离不一定经过自己，比如下面
        /**
         *      o (根)
         *    o
         *   o o
         *  o    o
         *         o
         *           o
         */
        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        int p3 = left.height + right.height + 1;
        int maxDistance =Math.max( Math.max(p1,p2) ,p3);

        return new Info(maxDistance,height);
    }

}
