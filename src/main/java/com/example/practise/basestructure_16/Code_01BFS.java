package com.example.practise.basestructure_16;

import com.example.practise.basestructure_16.common.Graph;
import com.example.practise.basestructure_16.common.Node;

import java.util.*;

/**
 * @author:haokanghao
 * @date: 2021/5/28 09:25
 * @desc: 宽度优先遍历
 */
public class Code_01BFS {

    // 宽度优先遍历
    public static void bfs (Node start){
        if(start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            for(Node node : poll.nexts){
                if(!set.contains(node)){
                    set.add(node);
                    queue.add(node);
                }
            }
        }
    }


    //深度优先遍历
    public static void dfs (Node node){
        if(node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            for(Node n : pop.nexts){
                if(!set.contains(n)){
                    set.add(n);
                    stack.push(pop);
                    stack.push(n);
                    System.out.println(n.value);
                    break;
                }
            }
        }
    }



    //拓扑
    public static List<Node> sortedTopology(Graph graph){
        // key 为某个节点 vaule 为入度值
        HashMap<Node,Integer> inMap = new HashMap<>();
        // 入度为0的节点
        Queue<Node> zeroInQueue = new LinkedList<>();
        //遍历节点 查找入度为0的节点
        for(Node node : graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in == 0){
                zeroInQueue.add(node);
            }
        }
        // 返回的顺序
        List<Node> result = new ArrayList<>();

        while (!zeroInQueue.isEmpty()){
            Node cur =  zeroInQueue.poll();
            result.add(cur);
            //消除该节点在图的影响 入度数-1
            for(Node n : cur.nexts){
                inMap.put(n,inMap.get(n)-1);
                if(inMap.get(n) == 0){
                    zeroInQueue.add(n);
                }
            }
        }
        return result;
    }






}
