# SpringBoot中使用Websocket
## 配置
WebsocketConfig这个文件中有StompEndpoint的配置，这个就是服务端的监听端点，本例中`stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();`的含义为客户端可以通过/endpointSang的Sockjs来连接上来。
`registry.enableSimpleBroker("/topic")`则是客户端可以监听的主题为`/topic/xxx`。
## 注解
```java
@MessageMapping("/welcome")
@SendTo("/topic/getResponse")
``` 
代表客户端send的/welcome主题的消息在此函数中接收，并将函数的返回值作为/topic/getResponse主题的内容发送。
## 主动发送
自动装配的`SimpMessagingTemplate`有convertAndSend方法可以主动发送消息。
## 运行
`mvn spring-boot:run`