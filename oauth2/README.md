# Spring Boot中使用Oauth2.0
## Oauth2.0
给第三方授权的一个规范，例如虎牙可以使用qq账号登录，这是使用了qq的Oauth服务。
## 关于本例
本例子提供了Oauth2.0的Auth服务器同时也是Resource服务器的配置，一般而言两者可以分开也可以在一起。至于客户端Spring官网中有Oauth2.0客户端的写法。
## 运行本例
`mvn spring-boot:run`  
直接访问 post http://localhost:8080----401错误 


#### 方法一 `password`方式获取access_token  
获取access_token post http://c:s@localhost:8080/oauth/token  
- 数据[以form形式提交参数不要用json格式]：
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
#### 方法二 `authorization_code`方式获取token
get http://frank:12345@localhost:8080/oauth/authorize?response_type=code&redirect_uri=http://localhost:3000&client_id=c
这里localhost:3000是我随便写的，用来充当调用方。调用后弹出界面问是否授权，选择授权后页面自动跳转  
get http://localhost:3000/?code=lRXNRM  
其中lRXNRM就是获取的code，然后调用方应该让用户的浏览器产生跳转到如下url[因为调用方没有登录状态只能让用户来完成操作]  
post http://c:s@localhost:8080/oauth/token
- 数据
```json
     {
         "grant_type":"authorization_code" ,
         "username":"http://localhost:3000",
         "password":"lRXNRM"
     }
```  
- 返回
```json
    {
        "access_token": "7bd7acaa-116c-4c2e-8df0-38ddd99588dd",
        "token_type": "bearer",
        "refresh_token": "6c4550a0-ec81-4211-9042-7efbd2d04934",
        "expires_in": 659,
        "scope": "read fuk"
    }
```
然后调用方让用户浏览器访问自己的url后面参数带上access_token和refresh_token，于是将token传给了调用方。
  
#### 再次访问 post http://localhost:8080 
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