package com.example.practise.basestructure_15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/5/26 19:16
 * @desc: https://leetcode.com/problems/number-of-islands-ii/
 */
public class Code03_NumberOfIslandsII {

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for(int[]position: positions ){
            ans.add(uf.connect(position[0],position[1])) ;
        }
        return ans;
    }



    public static class UnionFind1 {
        // parent[i] = k 标识i节点的父的下标是K
        private int[] parent;
        //size[i] 只有顶级的父节点才有用 标识有多少size
        // 用来决定 两个根节点相互比较 谁挂谁
        private int[] size;
        // 协助整理父亲节点
        private int[] help;
        private int sets;
        private final int row;
        private final int col;

        public UnionFind1(int m,int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        // 合并
        private void union(int r1, int c1, int r2, int c2) {

            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }

            int i1 = index(r1, c1);

            int i2 = index(r2, c2);
            // 如果有一个是0 说明还有被初始化 直接返回
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);

            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            // 初始化
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

    }
}
