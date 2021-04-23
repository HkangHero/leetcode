package com.example.practise.basestructure_08;

/**
 * @author:haokanghao
 * @date: 2021/4/23 09:37
 * @desc: 回文检测
 */
public class Code02_huiwenLinked {

    static class Node{
        Node next;
        int  value;
        public  Node(int value){
            this.value = value;
        }
    }

    /**
     * 找到中间节点
     * 奇中 偶上中
     */
    public static Node  queryMidNode(Node head){
        if( head.next == null){
            return head;
        }
        Node slow = head;
        Node index = head;
        while (index!=null&&index.next!=null){
            index = index.next;
            if(index.next!=null){
                index = index.next;
                slow = slow.next;
            }
        }
        return slow;
    }

    /**
     * 单链表反转
     */
    public static Node changeHead(Node head){
        if(head.next == null){
            return head;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 检测两个Node是否是回文数
     */
    public static boolean checkPalindrome (Node left,Node right){
        Node lindex = left;
        Node rindex = right;
        while (lindex!=null&&rindex!=null){
            if(lindex.value != rindex.value){
                return false;
            }
            lindex = lindex.next;
            rindex = rindex.next;
        }
        return true;
    }


    //将两个链表拼接
    public static Node listConnect(Node left,Node right){
        Node index = left;
        while (index!=null){
            Node p = index;
            if(index.next == null){
                p.next = right;
                break;
            }
            index = index.next;
        }
        return  left;
    }

    public static void  printNode(Node head){
        while (head!=null){
            System.out.print( head.value);
            head = head.next;
        }
        System.out.println();
    }

    /**
     * todo:  core
     * @param head
     * @return
     */
    public static  boolean process(Node head){
        if(head == null){
            throw  new RuntimeException("链表不能为空");
        }
        if(head.next == null){
            return true;
        }
        /**
         * 1、先找中间节点
         * 2、然后将中间节点的下一个节点反转
         * 3、然后将两个连接进行对比
         * 4、将后半截反转的链表再次逆转
         * 5、将两个单链表再次合并成原来的
         */
        Node midNode = queryMidNode(head); // 查找中间节点
        Node lastNode = midNode.next;

        midNode.next = null; // 因为要比较 所有必须将链表从中间断开

        Node node = changeHead(lastNode);// 后半部分反转
        boolean b = checkPalindrome(head, node);
        //要将连接接回来
        //1、先将后面部分的进行反转
        Node transNode = changeHead(node);
        //2、拼接
        head = listConnect(head, transNode);
        printNode(head);
        //3、打印
        return  b;
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(1);
        test.next = new Node(2);
        test.next.next = new Node(2);
        test.next.next.next = new Node(1);
//        test.next.next.next.next = new Node(5);
//        test.next.next.next.next.next = new Node(6);
//        test.next.next.next.next.next.next = new Node(7);
//        test.next.next.next.next.next.next.next = new Node(8);
//        test.next.next.next.next.next.next.next.next = new Node(9);


       System.out.println(process(test));
    }

}
