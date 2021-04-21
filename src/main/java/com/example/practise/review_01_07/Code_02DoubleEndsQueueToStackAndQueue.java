package com.example.practise.review_01_07;

import lombok.Data;

/**
 * @author:haokanghao
 * @date: 2021/4/21 13:49
 * @desc:  双向链表实现 栈 和 队列
 */
public class Code_02DoubleEndsQueueToStackAndQueue {

    @Data
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;
        public Node(T data) {
            value = data;
        }
    }

    //todo:  双向链表组成双端队列
    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        /**
         * 从对头加入
         * 1、如果刚开始为空，那么直接插入
         * 2、
         * @param value
         */
        public synchronized void addFromHead(T value) {
            Node newNode = new Node(value);
            if(head == null){
                head = newNode;
                tail = newNode;
            }else{
                newNode.next = head;
                head.last = newNode;
                head = newNode;
            }
        }
        // 从队尾加入
        public synchronized void addFromBottom(T value) {
            Node newNode = new Node(value);

            if(tail == null){
                head = newNode;
                tail = newNode;
            }else{
                tail.next  = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }
        // 从对头弹出  先考虑如果只有一个元素直接弹出，否者指针下移
        public  synchronized T popFromHead() {
            if(head == null){
                throw new RuntimeException("没有元素");
            }
            T value = head.value;
            if(head == tail){
                head = null;
                tail = null;
            }

            head = head.next;
            head.last = null;

            return value;
        }
    }




}
