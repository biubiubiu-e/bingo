package demo;


import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @Description：split的坑
 * 使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无
 * 内容的检查，否则会有抛 IndexOutOfBoundsException 的风险。
 * @Author: xfh
 * @Date: 2018/12/29 18:44
 */

public class testString {
    public static void main(String[] args) {
        String str = "a";
        //String str = "";
        String[] ary = str.split(",");
        int[] arr ;
        // 预期大于 3，结果是 3
        for(String a:ary){
            System.out.println(a);
        }
        System.out.println(ary.length);
        System.out.println("--------");
        String[] array = StringUtils.tokenizeToStringArray(str,",");
        Arrays.asList(array).stream().forEach(System.out::println);
    }
}
