package com.example.practise.basestructure_11;

/**
 * @author:haokanghao
 * @date: 2021/5/8 10:15
 * @desc: 给你二叉树的某个节点，返回该节点的后续节点
 */
public class Code06_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }
    public static Node getSuccessorNode(Node node) {
        if(node == null){
            return  null;
        }
        if(node.right != null){
            //找到右树最左
           return getLeftMost(node.right);
        }else{
            //如果右树为空，需要一直找父节点，直到该节点是上一个节点的左孩子。
            Node parent = node.parent;
            while (parent!=null && parent.right == node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    // 找到最左树
    private static Node getLeftMost(Node node) {
        if(node == null){
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
}
