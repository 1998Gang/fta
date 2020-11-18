package cn.ezs.fta;

import java.util.Scanner;

/**
 * 转换问题就是找到这个字符串中，两个相等字串的最大长度。
 */
public class Main {
    public static void main(String[] args) {

        /*String s="abcabclkjilkjilkkuhkjl;j;lk";
        int i1 = maxLenStr(s, s.length());
        System.out.println(i1);*/


        //输入
        Scanner scanner = new Scanner(System.in);
        //字符串长度
        int length=scanner.nextInt();
        //字符串
        String str = scanner.next();
        //获取最长的相同字串长度
        int i = maxLenStr(str, length);
        //结果就是长度减去最长相同字串长度-1；
        System.out.println(i==0?length:length-i+1);
    }

    public static int maxLenStr(String str, int length){
        int k=0;
        int max=0;
        for (int i=1;i<length;i++){
            for (int j=0;j<length-i;j++){
                if (str.charAt(j)==str.charAt(i+j)){
                    k++;
                }else {
                    k=0;
                }

                if (k>max){
                    max=k;
                }
            }
            k=0;
        }
        return max;
    }
}
