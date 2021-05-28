package com.example.practise.basestructure_16.common;

/**
 * @author:haokanghao
 * @date: 2021/5/28 08:45
 * @desc:  边
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
