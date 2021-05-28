package com.example.practise.basestructure_14;

import java.util.Arrays;
import java.util.Comparator;


/**
 * @author:haokanghao
 * @date: 2021/5/22 09:58
 * @desc: 贪心算法 会议室问题
 *
 * 贪心策略； 先给所有得时间段排序，按照end时间点最早得先排，不能按照会议室得开会长短选择
 *
 */
public class Code03_BestArrange {

    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static class ProgramComparator implements Comparator<Program> {
        // < 0 排前面
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    //贪心解决
    public static int bestArrange2(Program[] programs){
        Arrays.sort(programs,new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        for(int i =0 ; i<programs.length; i++){
            if(timeLine <= programs[i].start){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    //对数器

    /**
     *
     * @param programs 剩下需要得安排得时间段
     * @param done 已经安排了多少个
     * @param timeLine 现在得时间点
     * @return
     */
    public static int process(Program[] programs,int done, int timeLine){
        if( programs == null){
            return done;
        }
        // 还有会议可以选择
        int max = done;
        for(int i =0; i<programs.length ; i++){
            if(programs[i].start >= timeLine){
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max,process(next,done+1,programs[i].end));
            }
        }
        return max;
    }

    //移除第几个会议室
    public static Program[] copyButExcept(Program[] programs,int i ){
        Program[] ans = new Program[programs.length-1];
        for(int t =0,index = 0; t<programs.length;t++){
            if(t!=i){
                ans[index] = programs[i];
                index++;
            }
        }
        return ans;
    }


}
