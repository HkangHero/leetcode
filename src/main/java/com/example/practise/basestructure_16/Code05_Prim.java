package com.example.practise.basestructure_16;

import com.example.practise.basestructure_16.common.Edge;
import com.example.practise.basestructure_16.common.Graph;
import com.example.practise.basestructure_16.common.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author:haokanghao
 * @date: 2021/5/31 09:19
 * @desc:
 */
public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        // 依次挑选的的边在result里
        Set<Edge> result = new HashSet<>();
        // 随便挑了一个点
        for (Node node : graph.nodes.values()) {
            if(!nodeSet.contains(node)){
                nodeSet.add(node);
                // 由一个点，解锁所有相连的边
                for(Edge edge: node.edges){
                    priorityQueue.add(edge);
                }
                while ( !priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    // 不含有的时候，就是新的点
                    if (!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            break;
        }
        return result;
    }




}
