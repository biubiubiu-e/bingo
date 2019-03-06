package demo;

import com.xfh.bingo.exception.BambooException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description：java.util.List中有一个subList方法，用来返回一个list的一部分的视图。
 *
 * List<E> subList(int fromIndex, int toIndex);
 * 它返回原来list的从[fromIndex, toIndex)之间这一部分的视图，之所以说是视图，是因为实际上，返回的list是靠原来的list支持的。
 *
 * 所以，你对原来的list和返回的list做的“非结构性修改”(non-structural changes)，都会影响到彼此对方。
 *
 * 所谓的“非结构性修改”，是指不涉及到list的大小改变的修改。相反，结构性修改，指改变了list大小的修改。
 *
 *
 *
 * 那么，如果涉及到结构性修改会怎么样呢？
 *
 * 如果发生结构性修改的是返回的子list，那么原来的list的大小也会发生变化；
 *
 * 而如果发生结构性修改的是原来的list（不包括由于返回的子list导致的改变），那么返回的子list语义上将会是undefined。在AbstractList（ArrayList的父类）中，undefined的具体表现形式是抛出一个ConcurrentModificationException。
 *
 * 因此，如果你在调用了sublist返回了子list之后，如果修改了原list的大小，那么之前产生的子list将会失效，变得不可使用。
 *
 *  ArrayList的subList结果不可强转成ArrayList，否则会抛出 ClassCastException
 * 异常， 即 java.util.RandomAccessSubList cannot be cast to java.util.ArrayList.
 * 说明： subList 返回的是 ArrayList 的内部类 SubList，并不是 ArrayList ，而是
 * ArrayList 的一个视图，对于 SubList 子列表的所有操作最终会反映到原列表上。
 * 在 subList 场景中， 高度注意对原集合元素个数的修改，会导致子列表的遍历、增加、
 * 删除均会产生 ConcurrentModificationException 异常。
 *
 *
 * Arrays.asList()同义
 * 【强制】使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方
 * 法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
 * 说明： asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。Arrays.asList
 * 体现的是适配器模式，只是转换接口，后台的数据仍是数组。
 * String[] str = new String[] { "you", "wu" };
 * List list = Arrays.asList(str);
 * 第一种情况： list.add("yangguanbao"); 运行时异常。
 * 第二种情况： str[0] = "gujin"; 那么 list.get(0)也会随之修改。
 *
 * @Author: xfh
 * @Date: 2019/1/2 10:50
 */

public class testSublist {
    public static void main(String[] args) {
        List<String> parentList = new ArrayList<String>();

        for(int i = 0; i < 5; i++){
            parentList.add(String.valueOf(i));
        }

        List<String> subList = parentList.subList(1, 3);
        for(String s : subList){
            System.out.println(s);//output: 1, 2
        }
        System.out.println("1==================");
        //non-structural modification by sublist, reflect parentList
        subList.set(0, "new 1");
        for(String s : parentList){
            System.out.println(s);//output: 0, new 1, 2, 3, 4
        }
        System.out.println("2==================");
        //structural modification by sublist, reflect parentList
        subList.add(String.valueOf(2.5));
        for(String s : parentList){
            System.out.println(s);//output:0, new 1, 2,    2.5, 3,    4
        }
        System.out.println("3==================");
        //non-structural modification by parentList, reflect sublist
        parentList.set(2, "new 2");
        for(String s : subList){
            System.out.println(s);//output: new 1, new 2
        }
        System.out.println("4==================");
        //structural modification by parentList, sublist becomes undefined(throw exception)
        /*parentList.add("undefine");
        try{
            for(String s : subList){
                System.out.println(s);
            }
            subList.get(0);
        }catch (Exception e){
            throw new RuntimeException();
        }*/
        //删除一个list的某个区段,利用sublist的幕后还是原来的list的这个特性
        subList.subList(1, 3).clear();
        for(String s : parentList){
            System.out.println(s);
        }
        System.out.println("5==================");

        //Instant.ofEpochMilli()

    }
}
