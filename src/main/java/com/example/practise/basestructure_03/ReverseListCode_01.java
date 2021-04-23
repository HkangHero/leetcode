package com.example.practise.basestructure_03;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/4/9 09:58
 * @desc: 单向链表/双向链表
 */
public class ReverseListCode_01 {

    @Data
    public static  class Node{
        public int value;
        public Node next;
    }

    /**
     * 单链表反转  返回的是尾巴 不是head
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while (head!=null){
           next = head.next;
           head.next = pre;
           pre = head;
           head =next;
        }
        return pre;
    }


    @Data
    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;
    }

    /**
     *  双向链表反转
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head!=null){
           next = head.next;
           head.next = pre;
           head.last = next;
           pre =head;
           head = next;
        }
        return pre;
    }

    /**
     *  随机生成单链表
     * @param len
     * @param value
     * @return
     */
    private static Node generateRandomLinkedList(int len, int value) {
        int size = (int)(Math.random()*(len+1));
        if(size == 0){
            return null;
        }
        size -- ;
        Node head =  new Node();
        head.setValue((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size!=0){
           Node cur = new Node();
           cur.setValue((int) (Math.random() * (value + 1)));
           pre.next = cur;
           pre = cur;
           size -- ;
        }
        return head;
    }

    /**
     * 将数据放入到容器中方便后续的检验
     * @param head
     * @return
     */
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head!=null){
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    /**
     * 单链表检验
     * @param linkedListOriginOrder
     * @param head
     * @return
     */
    private static boolean checkLinkedListReverse(List<Integer> linkedListOriginOrder, Node head) {
        for(int i =linkedListOriginOrder.size()-1;i>=0;i--){
            if(!linkedListOriginOrder.get(i).equals(head.getValue())){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 生成双向链表
     * @param len
     * @param value
     * @return
     */
    private static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int)(Math.random()*(len+1));
        if(size == 0){
            return  null;
        }
        size --;
        DoubleNode head = new DoubleNode();
        head.setValue((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size!=0){
            DoubleNode cur = new DoubleNode();
            cur.setValue((int) (Math.random() * (value + 1)));
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    /**
     * 双向链表对数器
     * @param origin
     * @param head
     * @return
     */
    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for(int i = origin.size()-1;i>=0;i--){
            if(!origin.get(i).equals(head.value)){
                return false;
            }
            end = head;
            head = head.next;
        }

        for(int i = 0;i<origin.size();i++){
            if(!origin.get(i).equals(end.value)){
                return false;
            }
            end = end.last;
        }
        return true;

    }
    public static void main(String[] args) {
        // 自定义对数器
        int len = 50;
        int value = 100;
        int testTime = 10000;
        System.out.println("test begin....");
        for(int i =0;i<20;i++){
            Node node1 = generateRandomLinkedList(len,value);
            List<Integer> linkedListOriginOrder = getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);//单链表反转
            if(!checkLinkedListReverse(linkedListOriginOrder,node1)){
              System.out.println("单链表反转失败..");
            }

            DoubleNode node2 = generateRandomDoubleList(len,value);
            List<Integer> doubleListOriginOrder = getDoubleListOriginOrder(node2);
            node2 = reverseDoubleList(node2);
            if(!checkDoubleListReverse(doubleListOriginOrder,node2)){
                System.out.println("双向表反转失败..");
            }
        }
        System.out.println("test end....");

    }




}
