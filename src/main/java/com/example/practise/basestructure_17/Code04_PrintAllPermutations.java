package com.example.practise.basestructure_17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:haokanghao
 * @date: 2021/6/1 09:44
 * @desc: 给一个字符串，求它的所有的排序方式， 比如abcd -> 看有多少种排序
 */
public class Code04_PrintAllPermutations {

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    private static void f(ArrayList<Character> rest, String path, List<String> ans) {
        if(rest.size() == 0){
            ans.add(path);
        }else{
            int N = rest.size();
            for(int i =0; i<N ; i++){
                char cur = rest.get(i);
                rest.remove(i);
                f(rest, path + cur, ans);
                rest.add(i, cur);
            }
        }
    }

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void g1(char[] str, int index, List<String> ans) {
        if(index == str.length){
            ans.add(String.valueOf(str));
        }else{
            for (int i = index; i < str.length; i++) {
                swap(str,index,i);
                g1(str,i,ans);
                swap(str,index,i);
            }
        }
    }


    // 去重版
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public static void g2(char[] str, int index, List<String> ans) {
        if(index == str.length){
            ans.add(String.valueOf(str));
        }else{
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if(!visited[str[i]]){
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }


}
