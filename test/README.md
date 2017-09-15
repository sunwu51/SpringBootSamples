# SpringBoot Test
## 一般的原则
1.分层测试  
2.spring上下文  
3.数据库回滚
## 简述
### web层测试：  
用`@MvcWebTest`注解的类，Controller在测试的时候运行。  
`MockMvc`能自动装配他可以请求某个url判断返回的值是否符合期望。  
注意该注解下只有Controller生效了，Controller中的bean不会生效。
### dao层测试：
用`@DataJpaTest`注解的类 仓库接口类可以进行注入，运行测试不启动web容器
同样只有这个仓库可以注入，项目中其他bean都不行。
### 整体测试：
用`@SpringBootTest`注解则完全按照主程序的spring上下文运行测试环境