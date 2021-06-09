package com.example.practise.basestructure_16;

import com.example.practise.basestructure_16.common.Edge;
import com.example.practise.basestructure_16.common.Node;

import java.util.HashMap;

/**
 * @author:haokanghao
 * @date: 2021/6/1 08:48
 * @desc: 改进方法
 */
public class Code07_Dijkstra2 {

    // 自定义数据结构
    public static class NodeRecord {
        public Node node; // 节点
        public int distance; //距离

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // 自定义加强堆
    public static class NodeHeap {
        private Node[] nodes;
        //倒排索引
        private HashMap<Node,Integer> heapIndexMap;

        //初始节点到该节点的距离  注： -1代表之前已经放过，后来移除了 与没有放入的进去区别
        private HashMap<Node,Integer> distanceMap;

        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 判断一个节点是否还在堆中
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        // 加强堆 因为更新的时候 只会等于小于 所以堆只会往上
        private void insertHeapify(Node node, int index) {
            // 本节点 和自己的父节点进行比较
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //弹出元素后 需要对堆进行重新整理
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                // 找到下层谁最小的， 左孩子还是右孩子的 index
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;

                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                // 节点 与子节点最小的交换
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1; // 继续
            }
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        // 增加 堆节点 或者修改
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // 更新
            if(inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            // 新增
            if(!isEntered(node)){
                nodes[size] = node;
                heapIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node, size++);
            }
            // 忽略
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1); // 将最后一个与它交换
            heapIndexMap.put(nodes[size-1],-1); // 将这个元素移除
            distanceMap.remove(nodes[size-1]); // 距离将它移除
            nodes[size - 1] = null;
            heapify(0,--size);
            return nodeRecord;
        }
    }


    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    // 每次找到最小的距离 根据他 修改其他距离  pop 后的数据是不能修改的
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        // 构建一个小顶堆
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        // 路径
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            // 找到最小的
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

}
