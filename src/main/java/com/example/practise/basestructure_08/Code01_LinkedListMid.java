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

   //偶数返回上中点  4个 返回第2个，奇数返回中点
    public static  int  findMid(Node head){
        Node  slow = head;
        Node  quick = head;
        while (quick!=null&&quick.next!=null){
            quick = quick.next;
            if(quick.next!=null){
                slow = slow.next;
                quick = quick.next;
            }else{
                break;
            }
        }
      return slow.value;
    }

    //偶数返回下中点  4个 返回第3个，奇数返回中点
    public static  int  findMid2(Node head){
        Node  slow = head;
        Node  quick = head;
        while (quick!=null&&quick.next!=null){
            quick = quick.next;
            slow = slow.next;
            if(quick.next!=null){
                quick = quick.next;
            }else{
                break;
            }
        }
        return slow.value;
    }


    /**
     *    规则： 快指针先走两个 如果在下一个不为空 慢指针才走
     *    偶数返回的上中的前一个 奇数范围的是中间的前一个
     */

    public static  int  findMid3(Node head){
        if(head.next == null&&head.next.next== null){
            return 0;
        }
        Node  slow = head;
        Node  quick = head.next;

        while (quick.next!=null&&quick.next.next!=null){
                quick = quick.next.next;
                if(quick!=null&&quick.next!=null){
                    slow = slow.next;
                }
        }
        return slow.value;
    }

    /**
     *    规则：
     *    偶数返回的下中的前一个 奇数范围的是中间的前一个
     */

    public static  int  findMid4(Node head){
        if(head.next == null&&head.next.next== null){
            return 0;
        }
        Node  slow = head;
        Node  quick = head.next;
        while (quick.next!=null&&quick.next.next!=null){
            quick = quick.next.next;
            slow = slow.next;
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
        test.next.next.next.next.next.next.next.next = new Node(9);


        System.out.println(findMid(test));
        System.out.println(findMid2(test));
        System.out.println(findMid3(test));
        System.out.println(findMid4(test));

    }


}
