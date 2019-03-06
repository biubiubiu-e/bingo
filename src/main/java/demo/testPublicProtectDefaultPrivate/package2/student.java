package demo.testPublicProtectDefaultPrivate.package2;

import demo.testPublicProtectDefaultPrivate.package1.Person;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/28 18:45
 */

public class student extends Person {
    Person p =  new Person();
    public void test(){
        System.out.println(p.uname);
    }
}
