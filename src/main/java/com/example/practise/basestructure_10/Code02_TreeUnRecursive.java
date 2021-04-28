package com.example.practise.basestructure_10;

import java.util.Stack;

/**
 * @author:haokanghao
 * @date: 2021/4/28 13:53
 * @desc: 非递归方式二叉树
 */
public class Code02_TreeUnRecursive {
    public static class Node{
        private int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    /**
     * 先序遍历采用栈
     * 先序遍历：根、左、右
     * 所以入栈的时候应该是先有右后左
     */
    public static void pre (Node head){
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack();
        stack.push(head);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.print(pop.value+" ");
            Node left = pop.left;
            Node right = pop.right;
            if(right!=null){
                stack.push(right);
            }
            if(left!=null){
                stack.push(left);
            }
        }
        System.out.println();
    }

    /**
     * 中序
     *    任何二叉树都可以按左边界分开
     *    左边界压栈 压到左树最底层，直到为空，然后弹出栈顶（子树的根） 在开始右树
     */
    public static void in (Node cur){
        if(cur == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null){
            if(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.print(cur.value+" ");
                cur = cur.right;
            }
        }
        System.out.println();
    }


    /**
     * 后续二叉树
     * 使用两个栈：A和B栈
     * A元素弹出元素放入B栈
     * 弹出的元素 先放左后放右，这样再次弹出就会放在B栈 弹出就是正确的后续顺序
     *
     */
    public static void pos1(Node head){
        if(head == null){
            return;
        }
        Stack<Node> lStack = new Stack<>();
        Stack<Node> rStack = new Stack<>();
        lStack.push(head);
        while (!lStack.isEmpty()){
            Node pop = lStack.pop();
            rStack.push(pop);
            Node left = pop.left;
            Node right = pop.right;
            if(left!=null){
                lStack.push(left);
            }
            if(right!=null){
                lStack.push(right);
            }
        }

        while (!rStack.isEmpty()){
            Node pop = rStack.pop();
            System.out.print(pop.value+" ");
        }

        System.out.println();

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        pre(head);
//        System.out.println("========");
        in(head);
//        System.out.println("========");
        pos1(head);
//        System.out.println("========");
    }



}
