package com.example.practise.basestructure_08;

/**
 * @author:haokanghao
 * @date: 2021/4/22 19:33
 * @desc:
 */
public class Code01_LinkedListMid {

    static class Node{
        public Node next;
        public Integer value;
        public Node(Integer value){
            this.value = value;
        }
    }

    //找到中点   偶数返回的上一个  快指针先走 判断慢指针
    public static  int  findMid(Node head){
        Node  slow = head;
        Node  quick = head;
        while (quick!=null&&quick.next!=null){
            slow = slow.next;
            if(quick.next!=null){
                quick = quick.next;
                quick = quick.next;
            }else{
                break;
            }
        }
      return slow.value;
    }

    //找到中点   偶数返回的下一个  慢指针先走 在判断快指针
    public static  int  findMid2(Node head){
        Node  slow = head;
        Node  quick = head;
        while (quick!=null&&quick.next!=null){
            slow = slow.next;
            if(quick.next!=null){
                quick = quick.next;
            }else{
                break;
            }
            if(quick.next!=null){
                quick = quick.next;
            }else{
                break;
            }
        }
        return slow.value;
    }



    public static  int  findMid3(Node head){
        if(head.next == null){
            return 0;
        }
        Node  slow = head;
        Node  quick = head;
        Node  shaobing = head.next;
        while (quick!=null&&quick.next!=null){
            if(shaobing.next!=null){
                shaobing = shaobing.next;
                slow = slow.next;
            }
            if(quick.next!=null){
                quick = quick.next;
            }else{
                break;
            }
            if(quick.next!=null){
                quick = quick.next;
            }else{
                break;
            }
        }
        return slow.value;
    }
    public static void main(String[] args) {
        Node test = null;
        test = new Node(1);
        test.next = new Node(2);
        test.next.next = new Node(3);
        test.next.next.next = new Node(4);
        test.next.next.next.next = new Node(5);
        test.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next = new Node(8);
//        test.next.next.next.next.next.next.next.next = new Node(9);
        int mid = findMid(test);

        System.out.println(findMid2(test));

    }


}
