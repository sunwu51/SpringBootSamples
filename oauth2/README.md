# Spring Boot中使用Oauth2.0
## Oauth2.0
给第三方授权的一个规范，例如虎牙可以使用qq账号登录，这是使用了qq的Oauth服务。
## 关于本例
本例子提供了Oauth2.0的Auth服务器同时也是Resource服务器的配置，一般而言两者可以分开也可以在一起。至于客户端Spring官网中有Oauth2.0客户端的写法。
## 运行本例
`mvn spring-boot:run`  
直接访问 post http://localhost:8080----401错误  
  
获取access_token post http://c:s@localhost:8080/oauth/token  
- 数据：
 ```json
     {
         "grant_type":"password" ,
         "username":"frank",
         "password":"12345"
     }
 ```
- 返回：  
```json
     {
          "access_token": "90eb7963-2662-41dc-b05d-23e8d429c7b9",
          "token_type": "bearer",
          "refresh_token": "436359e0-7eac-4046-8aaa-81cecfaaca62",
          "expires_in": 659,
          "scope": "fuk read"
      } 
```  
  
  
再次访问 post http://localhost:8080 
```
Authorization:Bearer 90eb7963-2662-41dc-b05d-23e8d429c7b9
```
访问成功  
  
如果将上述token写错---401错误
如果将上述url改为/admin---403错误【因为配置了hasRole】
## Scope和Role
我们发现可以配置hasRole和access("#oauth2.hasScope('xxx')"),如果已经有了Role我们为什么还要配置scope呢？  
  
Role是对于用户而言的如上面的frank用户，在申请token的时候我们需要传递frank的账户信息同时还传递了client:secret，这相当于两套账号密码。frank是用户它具有Role，c则是client他就有scope。  
  
一般服务端提供几个clientid分别具有相应的scope例如只能读头像和昵称信息和也能查看个人照片的两种scope。这样把两个scope分别给不同的合作商，例如csdn需要有获取照片的权限则要第二种scope的clientid而虎牙只需要昵称和头像则需要第一种scope即可。
  
而Role则是这个普通qq用户只能获取自己的相关信息而不能获取管理员角色才能访问资源。  