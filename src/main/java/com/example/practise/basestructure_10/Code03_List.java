package com.example.practise.basestructure_10;

/**
 * @author:haokanghao
 * @date: 2021/4/28 15:23
 * @desc:
 * 给定两个可能有环也 可能无环 的单链表，头节点head1和head2.
 * 请实现一个函数，如果两个链表相交请返回相交得第一个节点，如果不相交返回null
 * 要求：如果两个链表长度之和N，时间复杂度达到O(N),额外空间O(1)
 *
 * leetcode 160
 */
public class Code03_List {
    public static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int data) {
            this.value = data;
        }
    }

    public  ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        if(head1 == null || head2 == null){
            return null;
        }
        ListNode  loop1 = getLoopNode(head1);
        ListNode  loop2 = getLoopNode(head2);

        if(loop1 == null && loop2 == null){
            // 两个链表 都不是环
            return noLoop(head1, head2);
        }
        if(loop1!=null&&loop2!=null){
            //两个链表都是环
             return    bothLoop(head1,loop1,head2,loop2);
        }

            //一个链表有环  一个链表无环 一定不会相交！
            return null;
    }




    /** 找到链表第一个入环的点 如果无环返回null
     * 定律：快慢指针在环内第一次相遇之后，头节点和这个点的中点就是入环的第一个节点
     * (在环内相遇之后，让快指针从头开始一步一步走，慢指针继续移动，相遇之后就是第一个节点)
     * @param head
     * @return
     */
    public  ListNode getLoopNode(ListNode head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        ListNode slow = head.next;
        ListNode quirt =  head.next.next;
        while (slow!=quirt){
            if (quirt.next==null || quirt.next.next==null){
                return null;
            }
            quirt   =  quirt.next.next;
            slow = slow.next;
        }
        quirt = head;
        while (slow!=quirt){
            quirt = quirt.next;
            slow = slow.next;
        }
        return slow;
    }


    // 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
    public  ListNode noLoop(ListNode head1, ListNode head2) {
        if(head1 == null || head2 == null){
            return  null;
        }
        int n = 0; //一个变量去判断哪个链表长
        ListNode lIndex = head1;
        ListNode rIndex = head2;
        while (lIndex.next!=null){
            n++;
           lIndex = lIndex.next;
        }
        while (rIndex.next!=null){
            n--;
            rIndex = rIndex.next;
        }
        //如果两个链表的尾指针不等 那么一定不相交
        if(lIndex!=rIndex){
            return null;
        }

        lIndex = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        rIndex = lIndex == head1 ? head2 : head1; // 谁短，谁的头变成cur2< head2Count ? head1 : head2;
        n = Math.abs(n); //将代表两个链表相差多少

        while (n!=0){
            n--;
           lIndex = lIndex.next;
        }

        while (lIndex != rIndex){
           lIndex = lIndex.next;
           rIndex = rIndex.next;
        }
       return lIndex;
    }


    /**
     * 两个有环链表，返回第一个相交节点，如果不想交返回null
     * @param head1  第一个头节点
     * @param loop1        环状第一个节点
     * @param head2  第二个头节点
     * @param loop2        环状第一个节点
     * @return 两个环状链表是否相连
     * 1、判断两个环是否相交
     *     一个链表先在环内自循环一次，如果碰不见另一个链表的入环点 说明两个不相交
     * 2、如果两个环相交 可能有两种情况
     *     1、两个链表同一个入环点
     *     2、两个链表不是同一个入环点，但两个都算是相交点。
     */
    public  ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        ListNode cur1 = null;
        ListNode cur2 = null;
        /**
         *  明确两个链表是相交的状态
         */
        if(loop1 == loop2){

            cur1 = head1;
            cur2 = head2;
            int n =0;

            while (cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }

            while (cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }

            //相交点就是入环点
            if(cur1 == cur2){
                return  cur1;
            }

            cur1 = n > 0 ? head1 : head2;  //长
            cur2 = cur1 == head1 ? head2 : head1; //短

            n = Math.abs(n);
            while (n > 0){
                n --;
                cur1 = cur1.next;
            }

            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
          return cur1;

        }else {
            //两个环状不在同一个
            ListNode cycle = loop1.next;
            while (cycle!=loop1){
                //这种情况就是两条链表同一个环，但是不是同一个切入点
                if(cycle == loop2){
                    return cycle;
                }
                cycle = cycle.next;
            }

            //两个链表都没有相交
            return  null;
        }

    }

    public static void main(String[] args) {

    }
}
