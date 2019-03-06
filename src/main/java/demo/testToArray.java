package demo;

/**
 * @Description：
 * 【强制】使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全
 * 一样的数组，大小就是 list.size()。
 * 说明： 使用 toArray 带参方法，入参分配的数组空间不够大时， toArray 方法内部将重新分配
 * 内存空间，并返回新数组地址； 如果数组元素大于实际所需，下标为[ list.size() ]的数组
 * 元素将被置为 null，其它数组元素保持原值，因此最好将方法入参数组大小定义与集合元素
 * 个数一致。

 * 反例： 直接使用 toArray 无参方法存在问题，此方法返回值只能是 Object[]类，若强转其它
 * 类型数组将出现 ClassCastException 错误。
 * @Author: xfh
 * @Date: 2019/1/2 12:36
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param
 * @param
 * @return
 */
public class testToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        list.add("c");
        //String[] array = new String[list.size()];
        String[] array = new String[0];
        //String[] array = new String[4];
        //array = list.toArray(array);//带参数方法
        array = (String[]) list.toArray();//无参数报错java.lang.ClassCastException
    }
}
