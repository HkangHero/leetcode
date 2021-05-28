package com.example.practise.basestructure_16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/5/28 15:32
 * @desc:
 */
public class Code03_TopologicalDFS {

    public static class DirectedGraphNode {
        int label;
        //该节点邻节点
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 自定义一个类，用来比较深度
    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    // 自定义比较器 谁的深度大 谁排在前面
    public static class MyComparator implements Comparator<Record>{
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 添加缓存
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        // 通过缓存找个这个节点下所有的缓存
        for(DirectedGraphNode cur : graph){
             f(cur,order);
        }
        // 开始排序
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
        recordArr.sort(new MyComparator());

        // 返回结果
        ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;

    }

    // 返回该节点的最大深度
     public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if(order.containsKey(cur)){
            return order.get(cur);
        }
        int folloew = 0;
        for(DirectedGraphNode next: cur.neighbors){
            folloew = Math.max(folloew,f(next,order).deep);
        }
        Record ans = new Record(cur,folloew+1);
        order.put(cur,ans);
        return  ans;
    }


}
