package com.example.practise.basestructure_12;

import java.util.LinkedList;

/**
 * @author:haokanghao
 * @date: 2021/5/20 16:16
 * @desc: 判断是否是一个完全二叉树
 */
public class Code01_IsCBT {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    //方法一：
    public static boolean idCBT1(Node node){
        if(node == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        boolean leaf = false; // 记录是否遇到过子节点不双全的
        Node l = null;
        Node r = null;
        queue.add(node);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            l = poll.left;
            r = poll.right;
            if(
                    // 如果遇到过子节点不双全 并且 再次遇到了子节点不为空 直接返回
                    (leaf && (l !=null || r != null) )
                    ||
                            //遇到了左节点为空 右节点不为空 直接返回
                    (l == null && r != null)
            ){
                 return  false;
            }

            if(l !=null){
                queue.add(l);
            }

            if(r != null){
                queue.add(r);
            }
            //设置 如果遇到了子节点不双全
            if(l == null || r == null){
                leaf = true;
            }
        }
        return  false;

    }


}
