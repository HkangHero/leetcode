package com.example.practise.basestructure_16.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/5/28 08:41
 * @desc:  点结构的描述
 */
public class Node {
    // 节点值
    public int value;
    //入度 多少个点直接指向它
    public int in;
    //出度 它直接指向其他点的数
    public int out;
    //  它指向的点
    public List<Node> nexts;
    // 从它出发的边
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
