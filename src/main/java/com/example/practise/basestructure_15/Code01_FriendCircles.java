package com.example.practise.basestructure_15;


/**
 * @author:haokanghao
 * @date: 2021/5/26 13:33
 * @desc:
 *
// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/
// 可以直接通过
 */
public class Code01_FriendCircles {

    public static  int findCircleNum(int[][] M){
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for(int i =0 ;i<N;i++){
            for(int j =i+1 ;j<N;j++){
                if(M[i][j] == 1){
                    unionFind.uniod(i,j);
                }
            }
        }
        return unionFind.sets();
    }


    public static class UnionFind{
        // 记录每个节点的父亲节点
        private int[] parent;
        //用于记录 这个节点所代表的集群的总数
        private int[] size;
        //辅助
        private int[] help;
        //总朋友圈总数
        private int sets;
        public UnionFind(int N){
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for(int i = 0;i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findParent(int i){
            int hi = 0;

            while ( i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }

            for( hi -- ;hi >=0; hi--){
                parent[help[hi]] = i;
            }
            return  i ;
        }

        public void uniod(int i ,int j){
            int f1 = findParent(i);
            int f2 = findParent(j);
            if(f1 != f2){
                if(size[f1] >= size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else{
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }


}
