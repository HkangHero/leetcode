package com.example.practise.basestructure_03;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author:haokanghao
 * @date: 2021/4/9 13:17
 * @desc: 双向链表实现 栈 和 队列
 */
public class DoubleEndsQueueToStackAndQueue_03 {

    @Data
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;
        public Node(T data) {
            value = data;
        }
    }

    // 双向链表组成双端队列
    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        /**
         * 从队列头加入
         * 1、头为空，没有元素 头指针、尾指针指向新的节点
         * 2、不未空，先将新的节点的next指向head，然后head的last指向新节点。
         * 3、最后将head 节点移动到新的节点上（head = node）
         * @param value
         */
        public synchronized void addFromHead(T value) {
            Node<T> cur =new Node(value); //新增的节点
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        /**
         * 从尾部增加 类似
         * @param value
         */
        public synchronized void addFromBottom(T value) {
         Node<T> cur = new Node<>(value);
         if(tail == null){
             tail = cur;
             head = cur;
         }else{
             cur.last = tail;
             tail.next = cur;
             tail = cur;
         }
        }

        /**
         * 从头出栈：
         * 1、如果头为空，说明没有元素了
         * 2、如果头尾相同，说明只有一个元素，弹出这个元素后，头 尾都要指向null
         * 3、如果头尾不同 指针移动即可 head指向下一个，下一个的last指向空
         * @return
         */
        public  synchronized T popFromHead() {
            if(head == null){
                return  null;
            }
            T value = head.value;
            if(head == tail){
                head = null;
                tail = null;
                return value;
            }
             head = head.next;
             head.last = null;
            return value;
        }

        /**
         * 从尾出
         * @return
         */
        public synchronized T popFromBottom() {
            if(tail == null){
                return  null;
            }
            T value = tail.value;
            if(tail == head ){
                tail = null;
                head = null;
                return value;
            }
            tail = tail.last;
            tail.next = null;
            return  value;
        }

        public synchronized boolean isEmpty() {
            return head == null;
        }
    }


    // 双端队列变成栈
    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue;
        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    //双端队列变成普通队列
    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        // 自定义对数器
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 10000;
        DoubleEndsQueue doubleEndsQueue = new DoubleEndsQueue();
       for(int i =0;i<testTimes;i++){
           MyStack<Integer> myStack = new MyStack<>();
           MyQueue<Integer> myQueue = new MyQueue<>();
           Stack<Integer> stack = new Stack<>();
           Queue<Integer> queue = new LinkedList<>();
           for (int j = 0; j < oneTestDataNum; j++) {
               int nums = (int) (Math.random() * value);
               if (stack.isEmpty()) {
                   myStack.push(nums);
                   stack.push(nums);
               } else {
                   if (Math.random() < 0.5) {
                       myStack.push(nums);
                       stack.push(nums);
                   } else {
                       if (!isEqual(myStack.pop(), stack.pop())) {
                           System.out.println("oops!");
                       }
                   }
               }
               int numq = (int) (Math.random() * value);
               if (stack.isEmpty()) {
                   myQueue.push(numq);
                   queue.offer(numq);
               } else {
                   if (Math.random() < 0.5) {
                       myQueue.push(numq);
                       queue.offer(numq);
                   } else {
                       if (!isEqual(myQueue.poll(), queue.poll())) {
                           System.out.println("oops!");
                       }
                   }
               }
           }
       }


    }



}
