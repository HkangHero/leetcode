package com.example.practise.exercise.day01;

/**
 * @author:haokanghao
 * @date: 2021/4/22 11:11
 * @desc:
 *  一个数组中只有两种字符 G  B  ，
 * // 可以让所有的G放在左边 让所有的B放在右边
 * //或者可以让所有的G放在右边， 所有的B放在左边
 * 但是只能在相邻字符之间进行交换操作，请问至少需交换几次
 */
public class Code04 {
    public static void main(String[] args) {
        String s ="BBG";
        if(s == null || s.equals("")){
            return ;
        }
        char[] str = s.toCharArray();
        int GMoveSteep = 0; // G的移动的次数
        int GIndex = 0; // GIndex 应该在位置的下标
        int BMoveSteep = 0;
        int BIndex = 0;
        //G在最左边
        for(int i =0; i<str.length;i++){
            if(str[i] == 'G' ){
                GMoveSteep+= i-(GIndex);
               GIndex++;
            }
            if(str[i] == 'B' ){
                BMoveSteep+= i-(BIndex);
                BIndex++;
            }
        }



        System.out.println(Math.min(GMoveSteep,BMoveSteep));

    }
}
