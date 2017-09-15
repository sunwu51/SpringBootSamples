# SpringBoot整合ApacheShiro
## 配置
项目中`ShiroConfiguration`这个类非常重要，它配置了Shiro所有的东西。包括配置jdbcRealm，配置spring下声明，配置filter，配置异常页面
## 过滤器
对于权限管理是通过配置过滤器实现的，例如在`ShiroConfiguration`中72行配置的。authc是需要身份认证perms["xxx,yyy"] roles["xxx,yyy"] anon也是常用的过滤器写法
## 注解
除了直接配置在过滤器中，也可以在action方法上加注解，参考`TestController`中的注解
## 运行
这个样例只需要`mvn spring-boot:run`即可运行