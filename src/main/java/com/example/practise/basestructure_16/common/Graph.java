package com.example.practise.basestructure_16.common;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author:haokanghao
 * @date: 2021/5/28 08:46
 * @desc: 图
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
