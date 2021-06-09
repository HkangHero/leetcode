package com.example.practise.basestructure_16;

import com.example.practise.basestructure_16.common.Edge;
import com.example.practise.basestructure_16.common.Graph;
import com.example.practise.basestructure_16.common.Node;

import java.util.*;

/**
 * @author:haokanghao
 * @date: 2021/5/28 17:15
 * @desc:
 * 最小生成树K算法
 * 从全局观察，每次都是从最小的边开始考虑，如果两个点不会生成闭环，那么就选择
 */
public class Code04_Kruskal {
    public static  class UnionFind{
        // 节点的父亲节点
        private HashMap<Node,Node> fatherMap;
        // 某个集合的代表节点  value key 所在集合的个数
        private HashMap<Node,Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node n){
            Stack<Node> stack = new Stack<>();
            while ( n !=fatherMap.get(n)){
                stack.add(n);
                n = fatherMap.get(n);
            }
            while (!stack.isEmpty()){
                fatherMap.put(stack.pop(),n);
            }
            return n;
        }

        public void union(Node a ,Node b){
            if(a == null || b==null){
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if(aDai != bDai){
                if( sizeMap.get(aDai) >= sizeMap.get(bDai)){
                    sizeMap.put(aDai,sizeMap.get(aDai)+sizeMap.get(bDai));
                    fatherMap.put(bDai,aDai);
                    sizeMap.remove(bDai);
                }else{
                    sizeMap.put(bDai,sizeMap.get(aDai)+sizeMap.get(bDai));
                    fatherMap.put(aDai,bDai);
                    sizeMap.remove(aDai);
                }

            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }
    }


    // 边排序
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


    public static Set<Edge> kruskalMST(Graph graph) {
        // 构建点的并查集
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());

        // 构建边的堆排序
        PriorityQueue<Edge> priorityQueue = new PriorityQueue(new EdgeComparator());
        for (Edge edge : graph.edges) { // M 条边
            priorityQueue.add(edge);  // O(logM)
        }

        Set<Edge> result = new HashSet<>();

        while (!priorityQueue.isEmpty()){
            Edge poll = priorityQueue.poll();
            //  不会构成环路
            if(!unionFind.isSameSet(poll.from,poll.to)){
                result.add(poll);
                unionFind.union(poll.from,poll.to);
            }
        }
         return result;
    }

}
