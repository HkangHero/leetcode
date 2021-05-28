package com.example.practise.basestructure_16;

import java.util.*;

/**
 * @author:haokanghao
 * @date: 2021/5/28 10:39
 * @desc:
 * https://www.lintcode.com/problem/127/
 * 拓扑排序
 */
public class Code03_Topological {
    // 题目数据结构
     public static class DirectedGraphNode {
          int label;
          //该节点邻节点
          List<DirectedGraphNode> neighbors;
          DirectedGraphNode(int x) {
              label = x;
              neighbors = new ArrayList<DirectedGraphNode>();
         }
      }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graphs) {
         // key 是node  value 这个节点入度的次数
        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();

        for(DirectedGraphNode graph : graphs){
            indegreeMap.put(graph,0);
        }

        for(DirectedGraphNode graph : graphs){
            for(DirectedGraphNode child : graph.neighbors){
                indegreeMap.put(child,indegreeMap.get(child)+1);
            }
        }

        // 存放信息
        Stack<DirectedGraphNode> stack = new Stack<>();
        for(DirectedGraphNode cur : indegreeMap.keySet()){
            if(indegreeMap.get(cur) == 0){
                stack.add(cur);
            }
        }

        ArrayList<DirectedGraphNode> resul = new ArrayList<>();
        while (!stack.isEmpty()){
            DirectedGraphNode pop = stack.pop();
            resul.add(pop);
            for(DirectedGraphNode  p :  pop.neighbors){
                indegreeMap.put(p,indegreeMap.get(p)-1);
                if( indegreeMap.get(p) == 0){
                    stack.add(p);
                }
            }
        }
      return  resul;

    }


}
