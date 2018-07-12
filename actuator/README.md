# Actuator in Spring Boot
actuator作用是监控整个项目的运行状态，依赖为
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
在微服务中用的很多，一般需要配合SpringSecurity进行认证后才能访问，如果想要不进行身份认证也能访问，需要添加配置
```
management.security.enabled=false
```
## 部分常用的path及其功能
```
health        健康状态磁盘状态
mappings      url列表即声明的bean
env           应用环境信息和系统环境变量
beans         所有注入的bean
loggers       程序的日志信息
configprops   配置信息包括自动配置
info          自定义信息可以在配置文件中设置
metrics       内存、线程等信息
dump          线程信息
trace         http请求trace跟踪信息
```