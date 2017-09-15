package top.microfrank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@SpringBootApplication
public class RedisApplication implements CommandLineRunner{

	@Autowired
	RedisTest redisTest;
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		redisTest.f1();
	}
//	@Bean
//	public RedisConnectionFactory redisConnectionFactory(){
//		JedisConnectionFactory redisConnectionFactory=new JedisConnectionFactory();
//		redisConnectionFactory.setHostName("localhost");
//		redisConnectionFactory.setUsePool(true);
//		return redisConnectionFactory;
//	}
}
