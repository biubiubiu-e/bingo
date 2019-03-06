package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：测试OOM异常
 * 内存设置-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 内存修改方式 Run  ---> Edit Config..   -->vm options
 * @Author: xfh
 * @Date: 2019/1/7 17:39
 */

public class testOOM {
    static class OOMObject{

    }
    /*public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }*/

    public static void main(String[] args) {
        testGC();
    }
    public  static void  testGC(){
        byte[] allo;
        allo = new  byte[4*1024*1024];
    }
}
