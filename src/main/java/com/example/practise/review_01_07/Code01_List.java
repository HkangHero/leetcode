package com.example.practise.review_01_07;

import com.example.practise.basestructure_03.ReverseListCode_01;

/**
 * @author:haokanghao
 * @date: 2021/4/21 10:38
 * @desc: 单向链表/双向链表
 */
public class Code01_List {

    public static  class  Node{
      public int value;
      public Node next;
  }

    //单链表反转
    public static Node reverseLinkedList(Node head){
       Node  p1 = null;
       Node p2 = null;
       while (head!=null){
           p2 = head.next;
           head.next = p1;
           p1 = head;
           head = p2;
       }
       return head;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;
    }
   //双向链表反转
    public static DoubleNode reverseLinkedList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next =null;
        while (head!=null){
           next = head.next;
           head.last = next;
           head.next = pre;
           pre = head;
           head = next;
        }
        return head;

    }

    //删除指定元素所有
    public static Node removeValue(Node head, int num){
        while (head!=null){
            if(head.value!=num){
                break;
            }
            head = head.next;
        }
       if(head == null){
           return null;
       }
       Node pre = head;
       Node next = head;
       while (next!=null){
            if(next.value == num){
                pre.next = next.next;
            }else{
                pre = next;
            }
           next = next.next;
       }
       return  head;

    }


}
