package com.xfh.bingo.service.Impl;

import com.xfh.bingo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/1 15:32
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key){
        return (String)redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key){
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Collection key){
        redisTemplate.delete(key);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key,timeout,timeUnit);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit){
        return redisTemplate.getExpire(key,timeUnit);
    }

    @Override
    public Boolean flushDB() {
        return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return true;
            }
        });
    }

    @Override
    public Long incr(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Long increment = valueOperations.increment(key, 1L);
        return increment;
    }

    @Override
    public Long decr(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.increment(key, -1L);
    }

    @Override
    public List<String> multiGet(Collection keys) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.multiGet(keys);
    }

    @Override
    public void multiSet(Map map) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.multiSet(map);
    }

    /**
     * @param key     分布式锁 唯一标识----------分布式锁标识
     * @param value   分布式锁 解锁密钥----------只有锁持有者才可以解锁 ,解锁即删除key
     * @param timeout 过期时间,单位毫秒----------保证持锁者挂掉，不会发生死锁
     * @return true get lock OK, false get lock NOT OK
     * @throws Exception
     * @author SuperHakce
     * Redis 实现分布式锁
     * 互斥性。在任意时刻，只有一个客户端能持有锁。
     * 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
     * 具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
     * 加锁和解锁必须是同一个客户端。
     */
    @Override
    public boolean distributedAddLock(String key, String value, Long timeout) throws Exception {
        //原子操作
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, value, "NX", "PX", timeout);
            }
        }).toString();
        if (null == result) {
            return false;
        }
        return result.equals("OK");
    }

    /**
     * @param key
     * @param value
     * @throws Exception
     * @author SuperHakce
     * 删除 Redis 分布式锁，要求 key，value 全部匹配上，才能删除
     * 建议 value = MD5（机器IP + 线程ID + 时间戳）
     */
    @Override
    public void distributedDeleteLock(String key, String value) throws Exception {
        if (null == key) return;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String valueOld = valueOperations.get(key);
        if (null != valueOld && valueOld.equals(value)) {//保证只有持锁者才能解锁
            redisTemplate.delete(key);
        }
    }

}
