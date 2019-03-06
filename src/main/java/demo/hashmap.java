package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/27 14:30
 */


public class hashmap {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("1",1);
        map.put("2",2);
        map.put(3,"4");
        map.put("abcd",4);
        map.put(2987074,4);
        map.get("1");
        Boolean flag = map.containsKey(3);
        System.out.println(flag);
        flag = map.containsValue("4");
        System.out.println(flag);
        Map map1 = new HashMap();
       /* map1.putAll(map);
        System.out.println(map1);*/
        System.out.println(((HashMap) map1).clone());
        Set set =map.entrySet();
        System.out.println(map);
        int n =10;
        System.out.println((int)(n/Math.pow(2,3)));
        String a = "abcd";
        Integer b = 2987074;
        System.out.println(a.hashCode()+"--"+b.hashCode());
        flag = a.equals(b);
        System.out.println(flag);
        System.out.println(a.hashCode() == b.hashCode()?true:false);
    }
}
