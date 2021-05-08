package com.example.practise.basestructure_11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author:haokanghao
 * @date: 2021/4/28 19:34
 * @desc: 二叉树遍历
 *   层序序列化/反序列化
 *   前序序列话/反序列化
 */
public class Code01_LevelTraversalBT {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 二叉树 按层遍历
     * 弹出就打印，有左先入左 有右入右
     * 周而复始
     */
    public static void level(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll);
            Node left = poll.left;
            Node right = poll.right;
            if(left!=null){
                queue.add(left);
            }
            if(right!=null){
                queue.add(right);
            }
        }
    }

    /**
     * 二叉树先序序列化
     */
    public static Queue<String> preSerial(Node node){
        Queue<String> queue = new LinkedList<>();
        pres(node,queue);
        return  queue;
    }
    public static void pres(Node node, Queue<String> queue) {
        if(node == null){
            queue.add(null);
        }else{
            queue.add(String.valueOf(node.value));
            pres(node.left,queue);
            pres(node.right,queue);
        }
    }

    /**
     * 二叉树反序列化
     */
    public static Node buildByPreQueue(Queue<String> queue){
        if(queue == null || queue.size() == 0){
            return null;
        }
        return preb(queue);
    }

    public static Node preb(Queue<String> queue) {
        String value = queue.poll();
        if(value == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(value));
        node.left = preb(queue);
        node.right = preb(queue);
        return node;
    }

    /**
     * 层序 序列化
     */
    public static Queue<String> levelSerial(Node head){
          Queue<String> queue = new LinkedList<>();
          if(head == null){
              queue.add(null);
          }else{
              queue.add(String.valueOf(head.value));
              Queue<Node> help = new LinkedList<>();
              help.add(head);
             while (help!=null){
                 Node poll = help.poll();
                 Node left = poll.left;
                 Node right = poll.right;
                 if(left!=null){
                     queue.add(String.valueOf(left.value));
                     help.add(left);
                 }else{
                     queue.add(null);
                 }
                 if(right!=null){
                     queue.add(String.valueOf(right.value));
                     help.add(right);
                 }else{
                     queue.add(null);
                 }
             }
          }
          return queue;
    }

    /**
     * 层序反序列化
     */
    public static Node buildByLevelQueue(Queue<String> queue){
        if(queue == null || queue.size() == 0){
            return  null;
        }
        Node head = generateNode(queue.poll());
        Queue<Node> help = new LinkedList<>();
        if(head != null){
            help.add(head);
        }
        Node node = null;
        while (!help.isEmpty()){
            node = help.poll();
            node.left = generateNode(queue.poll());
            node.right = generateNode(queue.poll());
            if(node.left!=null){
                help.add(node.left);
            }
            if(node.right!=null){
                help.add(node.right);
            }
        }
        return node;
    }

    private static Node generateNode(String poll) {
        if(poll == null){
            return null;
        }
        return new Node(Integer.valueOf(poll));
    }






}
