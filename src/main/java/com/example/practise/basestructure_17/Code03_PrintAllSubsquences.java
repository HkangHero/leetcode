package com.example.practise.basestructure_17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/6/1 09:35
 * @desc: 求一个字符串的所有子序列
 * "abc" -> ""  a  ab  ac abc
 */
public class Code03_PrintAllSubsquences {

    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    private static void process1(char[] str, int i, List<String> ans, String path) {
        if(str.length == i){
            ans.add(path);
            return;
        }else{
            process1(str,i+1,ans,path+str[i]);
            process1(str,i+1,ans,path);
        }
    }

    // 没有重复的子序列
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    private static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);

    }


        public static void main(String[] args) {
        List<String> abc = subs("acccc");
            List<String> list = subsNoRepeat("acccc");
            System.out.println(abc);
        System.out.println(abc.size());
        System.out.println(list);
    }
}
