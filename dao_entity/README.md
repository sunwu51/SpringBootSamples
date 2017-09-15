# SpringBoot Dao
## Spring-Data Jpa
- 添加依赖
- Entity类
- 继承JpaRepository接口
- 注入即可使用
## Spring-Data Rest
- 添加依赖
- 添加注解RepositoryRestResource
## ManyToOne
- json转换的时候，外联的也会被转换  
`{"sid":1,"name":"frank","claz":{"cid":1,"cname":"1班"}}`
- 注意需要屏蔽掉Hibernate自带的字段  
`@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })`  
 否则报错No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer....
- 如果设置了懒加载则查询执行两个，如果不是懒加载则通过left join执行一个查询  
- 如果不需要返回外联的数据，想要这种形式
`{"sid":1,"name":"frank","claz":1}`  
则需要在claz这个字段上添加两个注解  
`@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cid")`  
`@JsonIdentityReference(alwaysAsId = true)`  
**但是一定要注意**  
即使不用加载claz的详情信息了，但是还是执行了查询，上述情况懒加载也会执行两个查询，立即加载会执行left outer  
- JsonIdentityInfo注解常用在关联模型的字段上面，防止无限嵌套  
## 运行
这个样例只需要`mvn spring-boot:run`即可运行