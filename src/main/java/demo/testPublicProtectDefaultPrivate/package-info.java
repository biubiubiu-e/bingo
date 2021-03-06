/**
 * @Description：
 * 接口开发准则
 * 接口类中的方法和属性不要加任何修饰符号（public 也不要加） ，保持代码的简洁性，并加上有效的 Javadoc 注释。
 * 尽量不要在接口里定义变量，如果一定要定义变量，肯定是与接口方法相关，并且是整个应用的基础常量。
 * 正例： 接口方法签名： void f();
 *        接口基础常量表示： String COMPANY = "alibaba";
 * 反例： 接口方法定义： public abstract void f();
 * 注： java8中
 * 方法默认public
 * 属性默认 public static final
 * @Author: xfh
 * @Date: 2018/12/28 18:50
 */
package demo.testPublicProtectDefaultPrivate;


/*java中四种访问修饰符区别及详解全过程
　　客户端程序员：即在其应用中使用数据类型的类消费者，他的目标是收集各种用来实现快速应用开发的类。

　　类创建者：即创建新数据类型的程序员，目标是构建类。　　

　　访问控制存在的原因：a、让客户端程序员无法触及他们不应该触及的部分  ； b、允许库设计者可以改变类内部的工作方式而不用担心会影响到客户端程序员

 　  java的四个关键字：public、protected、default、private（他们决定了紧跟其后被定义的东西可以被谁使用）

适用范围<访问权限范围越小，安全性越高>

　　　　  访问权限   类   包  子类  其他包

  　　　　  public     ∨   ∨    ∨     ∨       （对任何人都是可用的）

   　　　　 protect    ∨   ∨   ∨     ×　　　 （继承的类可以访问以及和private一样的权限）严格的家长，与其他包划清界限，其他包中的子类仍然不允许调用protect修饰的 变量，方法

   　　　　 default    ∨   ∨   ×     ×　　　 （包访问权限，即在整个包内均可被访问）

   　　　　 private    ∨   ×   ×     ×　　　 （除类型创建者和类型的内部方法之外的任何人都不能访问的元素）


   */