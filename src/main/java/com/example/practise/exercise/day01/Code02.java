package com.example.practise.exercise.day01;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author:haokanghao
 * @date: 2021/4/22 10:11
 * @desc: 给定一个文件目录路径，求这个路径下所有文件数量，隐藏文件也算，文件夹不算
 */
public class Code02 {


    public static int getFilNumer(String url){
        File file = new File(url);
        if(file.isFile()){
            return 1;
        }
        Queue<File> queue = new ArrayDeque<>();
        int count =0;
        queue.add(file);
        while (!queue.isEmpty()){
            File folder = queue.poll();
            for(File next : folder.listFiles()){
                if(next.isFile()){
                    count++;
                }
                if(next.isDirectory()){
                    queue.add(next);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String url = "D://chrom";
      System.out.println(getFilNumer(url));
    }
}
