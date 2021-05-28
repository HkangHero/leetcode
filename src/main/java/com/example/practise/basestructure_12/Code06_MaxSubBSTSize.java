package com.example.practise.basestructure_12;

/**
 * @author:haokanghao
 * @date: 2021/5/20 19:38
 * @desc:
 * X不做头
 * X左maxBSTSubSize
 * X右maxBSTSubSize
 *
 * X做头
 * X左是不是BST  X右树整体是不是BST
 * 如果是：X左max X右min
 * 总节点：X左size  X右size
 *
 *
 */
public class Code06_MaxSubBSTSize {

    public static class Node{
        private int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }


    public static class Info{
        public  int maxBSTSubtreeSize; //满足最大搜索树的子树size
        public int allSize; // 整个树的值 （maxBSTSubtreeSize ！=  allSize 就不是搜索树）
        public int max; //最大值
        public int min; // 最小值

        public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node node){
        if(node == null){
            return  null;
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int maxBSTSubtreeSize;
        int allSize = 1; // 当前节点
        int max = node.value;
        int min = node.value;
        // 1、先判断最大值 与 最小值 左右树
        if(left != null){
            max = Math.max(left.max,max);
            min = Math.min(left.max,min);
            allSize += left.allSize;
        }

        if(right != null){
            max = Math.max(right.max,max);
            min = Math.min(right.max,min);
            allSize += right.allSize;
        }

        // 判断最大子搜索二叉树的个数 三种情况
        //1、左树的最大
        int p1 = -1;
        if(left != null){
            p1 = left.maxBSTSubtreeSize;
        }

        int p2 = -1;
        // 右树的最大
        if(right != null){
            p2 = right.maxBSTSubtreeSize;
        }
        // 加上该节点
        int p3 = -1;
        boolean leftBST = left == null ? true : (left.maxBSTSubtreeSize == left.allSize);
        boolean rightBST = right == null ? true : (right.maxBSTSubtreeSize == right.allSize);
        // 如果左树 和右 树都是搜索树
        if(leftBST && rightBST){
            boolean leftMaxLessX = left == null ? true : (left.max < node.value);
            boolean rightMinMoreX = right == null ? true : (right.min > node.value);
            //如果加上该节点也符合
            if(leftMaxLessX && rightMinMoreX){
                // 临界值： 防止空
                int leftSize = left == null ? 0 : left.allSize;
                int rightSize = right == null ? 0 :right.allSize;
                p3 = leftSize + rightSize +1;
            }
        }
        maxBSTSubtreeSize =Math.max(Math.max(p1,p2),p3);
        return new  Info(maxBSTSubtreeSize,allSize,max,min);

    }



}
