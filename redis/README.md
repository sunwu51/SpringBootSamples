#SpringBoot自动装配Redis
只需要引入相关依赖则已经完成了RedisTemplate的注入，默认是本机6379端口无密码。如果想要修改可以直接在配置文件修改。
## Spring-Data-Redis
依赖于基本的redis处理库，例如可以是Jedis，本例中用的即Jedis也可以用其他的，具体的另外几种参考官网
## 运行
这个样例需要有个本地的redis服务器，随后`mvn spring-boot:run`即可运行