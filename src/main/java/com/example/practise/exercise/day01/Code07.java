package com.example.practise.exercise.day01;

import java.util.HashMap;

/**
 * @author:haokanghao
 * @date: 2021/4/22 14:03
 * @desc:
 */
public class Code07 {


    public static int findTargetSumWays(int[] nums, int target) {


//       return process(nums,0,target);
        return process2(nums,0,target,new HashMap<Integer,HashMap<Integer,Integer>>());

    }

    // 暴力

    /**
     * 可以自由使用index后的数 目标target 返回值是有几种方法
     */
    private static int process(int[] nums, int index, int target) {
        if(index == nums.length){ // 没有数了
           return target == 0 ? 1:0;
        }
           // 前面是加了index 所以要减target                            后面是减了index 所以target
       return process(nums,index+1,target-nums[index])+process(nums,index+1,nums[index]+target);
    }



    // 增加了缓存  记忆化搜索 17-38体系版
    // key 是index  value： target  functionNumber
    // index = 7  target=32  1中方法
    private static int process2(int[] nums, int index, int target, HashMap<Integer,HashMap<Integer,Integer>> map) {
       // 如果要查询的目标已经存在直接返回 不需要在递归
        if(map.containsKey(index)&&map.get(index).containsKey(target)){
            return  map.get(index).get(target);
        }
        int ans = 0;
        if(index == nums.length){
            return  target == 0 ? 1:0;
        }else{
            ans= process2(nums,index+1,target-nums[index],map)+process2(nums,index+1,nums[index]+target,map);
        }
        if(!map.containsKey(index)){
            map.put(index,new HashMap<Integer, Integer>());
        }
        map.get(index).put(target,ans);
        return ans;
    }

    /**
     * 优化点 常数时间
     * 1、全变成正数 不印象结果
     * 2、 如果累加和 < target ,直接返回0
     * 3、如果有的数加起来是奇数 而target是偶数 肯定不会有
     *     同一批数 不管怎么加减最后的奇偶性是一定的 如果奇偶都不相同的话 那么肯定不会存在
     * 4、所有取正的数P{1,3,5} 负的数是N{2,4} 如果tager存在 =》 P+N = tager =>  2*P = tager + sum =>P = (tager +sum)/2
     *      只要存在一个集合P 那么就一定存在一种Tager方法
     * 5、
     *
     */
    private static int process2(int[] nums, int target){
        int sum = 0;
        for(int i :nums){
            sum+=i;
        }
      return   (sum < target)|| ((sum & 1)^( target & 1))!=0 ? 0 : subset(nums,(target+sum)>>1);
    }

    private static int subset(int[] nums, int s) {
        int[] dp = new int[s+1];
        dp[0] = 1;
        for(int n : nums){
            for(int i =s ;i>=n;i--){
                dp[i] += dp[i-n];
            }
        }
        return dp[s];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int targetSumWays = findTargetSumWays(arr, 2);
        System.out.println(targetSumWays);

    }
}
