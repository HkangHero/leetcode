package com.example.practise.basestructure_16.common;

/**
 * @author:haokanghao
 * @date: 2021/5/28 08:50
 * @desc: 将不同的数据结构转换成自己的模型
 */
public class GraphGeneerator {

    //weight  from   to
    //   [5,    0,    7}
    //   [3,    0,    1]
    public static Graph createGraph(int[][] matrix){

        Graph graph = new Graph();
        for(int i =0; i<matrix.length ; i++){
            // 拿到每一条边
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            // 1 创建点
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }

            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

            // 建立一条新的边
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge newEdge = new Edge(weight,fromNode,toNode);

            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);


            graph.edges.add(newEdge);

        }
        return graph;
    }
}
