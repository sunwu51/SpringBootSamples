package top.microfrank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Created by Frank Local on 2017/9/13.
 */
@Component
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;


    public void f1(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("auth","4");
        System.out.println( redisTemplate.opsForValue().get("auth"));
    }
}
