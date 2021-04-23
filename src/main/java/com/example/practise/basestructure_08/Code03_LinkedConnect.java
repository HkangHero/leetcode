package com.example.practise.basestructure_08;

/**
 * @author:haokanghao
 * @date: 2021/4/23 11:01
 * @desc:  链表交替进行
 */
public class Code03_LinkedConnect {

    static class Node{
        public Node next;
        public int value;

        public Node(int value){
            this.value = value;
        }
    }



    public static void  printNode(Node head){
        while (head!=null){
            System.out.print( head.value);
            head = head.next;
        }
        System.out.println();
    }


    public static void  LinkedConnect(Node left,Node right){
            Node p1;
            Node p2;
            Node index = left;
            while (index!=null&&right!=null){
                p1 = index.next;
                index.next = right;
                p2 = right.next;
                //todo: 判断右边会短链
                if(p1 != null){
                    right.next = p1;
                }
                index = p1;
                right = p2;
            }
    }



    public static void main(String[] args) {
        Node test = null;
        test = new Node(1);
        test.next = new Node(2);
        test.next.next = new Node(3);
        test.next.next.next = new Node(4);

        Node test2 = null;
        test2 = new Node(5);
        test2.next = new Node(6);
        test2.next.next = new Node(7);
        test2.next.next.next = new Node(8);

        LinkedConnect(test, test2);
        printNode(test);


    }


}
