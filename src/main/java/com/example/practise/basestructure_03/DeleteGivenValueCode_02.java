package com.example.practise.basestructure_03;

import lombok.Data;

/**
 * @author:haokanghao
 * @date: 2021/4/9 10:58
 * @desc: 链表删指定值
 */
public class DeleteGivenValueCode_02 {
    @Data
    public static  class Node{
        public int value;
        public Node next;
    }

    /**
     * 删除节点中指定的值：
     * 1、先找到不是这个数的node作为head ，因为可能第一个数就是指定删除的数
     * 2、
     * @param head
     * @param num
     * @return
     */
    public static Node removeValue(Node head, int num){

        while (head!=null){
            if(head.getValue() != num){
                break;
            }
            head = head.next;
        }
        if(head == null){
            return null;
        }
        Node cur = head;
        Node pre = head;
        while (cur!=null){
            if(cur.getValue()== num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
