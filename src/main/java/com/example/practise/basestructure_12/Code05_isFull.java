package com.example.practise.basestructure_12;

/**
 * @author:haokanghao
 * @date: 2021/5/20 19:13
 * @desc: 是否是满二叉树  h 层 节点一共有 2^h -1个
 */
public class Code05_isFull {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }
    public static class Info{
        public int height; //最大高度
        public int nodes; // 节点数
        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }


    public static boolean isFull(Node node){
        if(node == null){
            return false;
        }
        int nodes = process(node).nodes;
        int height = process(node).height;
        return  (1 << height)-1 == nodes;
    }

    public static Info process (Node node){
        if(node == null){
            return  new Info(0,0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height,right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new Info(height,nodes);
    }


}
