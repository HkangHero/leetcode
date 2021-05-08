package com.example.practise.basestructure_11;

import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/5/7 17:17
 * @desc:
 */
public class Code03_EncodeNaryTreeToBinaryTree {

    //多叉树
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // 二叉树
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }




}
