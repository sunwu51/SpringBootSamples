# Spring Boot中使用Spring Security
## Spring Security
维护web程序的安全，例如身份认证（账户登录）、权限管理、Basic认证、csrf保护、xss保护、frameoptions设定等等。SpringSecurity的功能繁杂，当然主要的还是身份和权限的使用。
## 与Spring Boot整合
得益于Spring Boot自动装配功能，只需要引入Security依赖就可以使用默认配置运行，不过我们还需要自定义配置。对于详细的用法介绍可以参考我的[gist](https://gist.github.com/sunwu51/eac8f64809ad934e9513ee2887980987)
## 项目简述
配置文件甚至不用写任何东西，主要的配置都在WebSecurityConfig这个类中。
## 运行
这个样例只需要`mvn spring-boot:run`即可运行