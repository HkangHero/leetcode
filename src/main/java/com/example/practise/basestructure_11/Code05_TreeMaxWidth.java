package com.example.practise.basestructure_11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author:haokanghao
 * @date: 2021/5/7 19:49
 * @desc:
 */
public class Code05_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 求二叉树最宽的层有多少个节点
     * 再当前层就像设置下一层的终止位，
     * 根节点开始，如果右树不空，将中止位设置为右树。否则左树
     * 每次弹出判断是否为中止位，如果是就要设置他的中止位
     * 还要设置下一层的最右节点
     * @param head
     * @return
     */
    public static int maxWidthNoMap(Node head){
        if(head == null){
            return  0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; //当层最右节点
        Node nextEnd = null; //下一层，最右节点是谁
        int max = 0;
        int curLeverlNodes = 0; //当层节点的总数
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            Node left = poll.left;
            Node right = poll.right;
            if(left != null){
                queue.add(left);
                nextEnd = left;
            }
            if(right != null){
                queue.add(right);
                nextEnd = right;
            }
            curLeverlNodes++;
            if(poll == curEnd){
                max = Math.max(curLeverlNodes,max);
                curLeverlNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
