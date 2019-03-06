package com.xfh.bingo.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description：
 * spring-data-redis针对jedis提供了如下功能：
 * 1. 连接池自动管理，提供了一个高度封装的“RedisTemplate”类
 * 2. 针对jedis客户端中大量api进行了归类封装,将同一类型操作封装为operation接口
 * ValueOperations：简单K-V操作  !!!!1
 * SetOperations：set类型数据操作
 * ZSetOperations：zset类型数据操作
 * HashOperations：针对map类型的数据操作
 * ListOperations：针对list类型的数据操作
 *
 * 3. 提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key，即
 * BoundKeyOperations：
 * BoundValueOperations
 * BoundSetOperations
 * BoundListOperations
 * BoundSetOperations
 * BoundHashOperations
 *
 * 4. 将事务操作封装，有容器控制。
 * 5. 针对数据的“序列化/反序列化”，提供了多种可选择策略(RedisSerializer)
 *
 * @Author: xfh
 * @Date: 2019/2/1 15:33
 */
public interface RedisService {

    /**
     * redis设置值
     *
     * @param key   key
     * @param value value
     *
     */
    void set(String key, String value);

    /**
     * redis获取值
     *
     * @param key key
     * @return String
     *
     */
    String get(String key);

    /**
     * redis删除值
     *
     * @param key key
     *
     */
    void delete(String key);

    /**
     * redis删除值
     *
     * @param key
     */
    void delete(Collection key);

    /**
     * @param timeout 基数
     * @description 设置过期时间
     * @author zhangyanglei
     * @date 2017/9/1 17:39
     */
    Boolean expire(String key, long timeout, TimeUnit timeUnit);

    /**
     * @param key
     * @param timeUnit 时间单位
     * @description 获取过期时间
     */
    Long getExpire(String key, TimeUnit timeUnit);

    Boolean flushDB();

    Long incr(String key);

    Long decr(String key);

    List<String> multiGet(Collection keys);

    void multiSet(Map<String, String> map);

    /**
     * @author SuperHakce
     * Redis 实现分布式锁
     * 互斥性。在任意时刻，只有一个客户端能持有锁。
     * 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
     * 具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
     * 加锁和解锁必须是同一个客户端。
     * @param key     分布式锁 唯一标识----------分布式锁标识
     * @param value   分布式锁 解锁密钥----------只有锁持有者才可以解锁
     * @param timeout 过期时间-------------------保证持锁者挂掉，不会发生死锁
     * @throws Exception
     * @return true get lock OK, false get lock NOT OK
     */
    boolean distributedAddLock(String key, String value, Long timeout) throws Exception;

    /**
     * @author SuperHakce
     * 删除 Redis 分布式锁，要求 key，value 全部匹配上，才能删除
     * 建议 value = MD5（机器IP + 线程ID + 时间戳）
     * @param key
     * @param value
     * @throws Exception
     */
    void distributedDeleteLock(String key, String value) throws Exception;
}
