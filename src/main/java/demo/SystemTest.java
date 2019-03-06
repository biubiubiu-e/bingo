package demo;

import com.xfh.bingo.BingoApplication;
import org.springframework.boot.SpringApplication;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/12 14:07
 */

public class SystemTest {
    public static void main(String[] args) throws InterruptedException{
        //程序退出
        //System.exit(0);
        //计时用，System.currentTimeMillis();
        Long begin = System.currentTimeMillis();
        Thread bingo = new Thread();
        bingo.start();
        bingo.sleep(4000);
        Long end = System.currentTimeMillis();
        System.out.println("时间间隔为："+(end-begin));
        //System.arraycopy\
        int s[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int d[] = { 15, 25, 35, 45, 55, 65, 75, 85, 95, 105};
        System.arraycopy(s,0,d,1,2);
        System.out.println(d[1]);
        System.out.checkError();
        //i/o缓冲区概念
        String x = "bomboo";
        PrintWriter printWriter = new PrintWriter(System.out);
        PrintWriter printWriter1 = new PrintWriter(System.out);
        //缓存默认8kb，即8192b,不超过，不读出/不写入
        char[] chars = new char[8193];
        char[] chars1 =new char[8192];
        Arrays.fill(chars,'x');
        Arrays.fill(chars1,'x');
        //printWriter.println(chars);
        printWriter.write(chars);
        System.out.println("\n---分割线---");
        printWriter1.println(chars1);
        //flush和close类似，强制输出缓存中的字符，即使缓冲区没有满
        System.out.println("---分割线1---");
        printWriter1.flush();
        System.out.println("---分割线2---");
        printWriter1.close();

        File file = new File(".","/test.txt");
        printWriter.write(x);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
    }
}
