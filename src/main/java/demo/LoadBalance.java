package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/20 14:00
 */

public class LoadBalance {
    public static void main(String[] args) {
        Map<String,Integer> countmap = new HashMap();
        countmap.put("192.168.1.100", 0);
        countmap.put("192.168.1.101", 0);
        countmap.put("192.168.1.102", 0);
        countmap.put("192.168.1.103", 0);
        for (int i = 0, time = 10; i < time; i++) {
            //random();
            //weightRandom();
            //roundRobin();
            String server = weightRoundRobin();

            Set<String> keySet = serverMap.keySet();
            for (Iterator<String> it = ((Set) keySet).iterator(); it.hasNext(); ) {
                if (it.next().equals(server)){
                    countmap.put(it.next(), countmap.get(it.next()) + 1);
                }
            }
        }

        Set<String> keySet = serverMap.keySet();
        for (Iterator<String> it = ((Set) keySet).iterator(); it.hasNext(); ) {
            System.out.println(it.next() + "调用次数： " + countmap.get(it.next()));
        }

    }

        private static Map<String, Integer> serverMap = new HashMap<String, Integer>() {{
            put("192.168.1.100", 1);
            put("192.168.1.101", 1);
            put("192.168.1.102", 50);
            put("192.168.1.103", 1);
        }};


    /*随机算法 Random
随机，按权重设置随机概率。在一个截面上碰撞的概率高，但调用量越大分布越均匀，
而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。*/
        public static void random () {
            List<String> keyList = new ArrayList<String>(serverMap.keySet());
            Random random = new Random();
            int idx = random.nextInt(keyList.size());
            String server = keyList.get(idx);
            System.out.println(server);
        }

        public static void weightRandom () {
            Set<String> keySet = serverMap.keySet();
            List<String> servers = new ArrayList<String>();
            for (Iterator<String> it = ((Set) keySet).iterator(); it.hasNext(); ) {
                String server = it.next();
                int weithgt = serverMap.get(server);
                for (int i = 0; i < weithgt; i++) {
                    servers.add(server);
                }
            }
            String server = null;
            Random random = new Random();
            int idx = random.nextInt(servers.size());
            server = servers.get(idx);
            System.out.println(server);
        }
/*轮询(Round Robbin)
当服务器群中各服务器的处理能力相同时，且每笔业务处理量差异不大时，最适合使用这种算法。
轮循，按公约后的权重设置轮循比率。存在慢的提供者累积请求问题，比如：第二台机器很慢，
但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。*/
        private static Integer pos = 0;
        public static void roundRobin () {
            List<String> keyList = new ArrayList<String>(serverMap.keySet());
            String server = null;
            synchronized (pos) {
                if (pos >= keyList.size()) {
                    pos = 0;
                }
                server = keyList.get(pos);
                pos++;
            }
            System.out.println(server);
        }
/*加权轮询(Weighted Round Robbin)
为轮询中的每台服务器附加一定权重的算法。比如服务器1权重1，服务器2权重2，服务器3权重3，则顺序为1-2-2-3-3-3-1-2-2-3-3-3- ......*/
        public static String weightRoundRobin () {
            Set<String> keySet = serverMap.keySet();
            List<String> servers = new ArrayList<String>();
            for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
                String server = it.next();
                int weithgt = serverMap.get(server);
                for (int i = 0; i < weithgt; i++) {
                    servers.add(server);
                }
            }
            String server = null;
            synchronized (pos) {
                if (pos >= keySet.size()) {
                    pos = 0;
                }
                server = servers.get(pos);
                pos++;
            }
            System.out.println(server);
            return server;
        }

}
