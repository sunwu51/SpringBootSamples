# Jooq with Spring Boot
# Jooq的功能
1 帮助生成sql，用java代码生成sql字符串   
2 生成的sql可以直接指定DataSource执行  
3 SpringBoot中可以自动注入DataSource，因而可以快捷执行  
4 可以替代JdbcTemplate作为一种数据库操作的执行器  
# 优点
1 像JdbcTemplate一样灵活，在小型项目中可以不创建实体类直接查询  
2 使用简单，比JdbcTemplate要简单，代码量少且易于维护。对sql语句中方法的封装很像php的一些框架（如lavarel）的查询方式    
# 缺点
1 与JdbcTemplate相比，`@Transactional`不生效，必须按照自己的事务执行方式
2 查询返回对象是`Result<>`，Orm映射需要自己完成（和JdbcTemplate类似）
# 题外话
classpath下的`schema.sql`是创建数据表的一些操作，`data.sql`则是插入数据的操作，两者在任何情况下都可以执行，注意data.sql中出现create等语句则不识别，只能来插入数据用。  
`import.sql`也是在初始化的时候执行的sql脚本，但是import.sql是Hibernate去识别的，也就是说在用`SpringDataJpa`的情况下才能生效，而且数据库ddl规则必须是create或create-drop。  
在本例中我们没有引入springdatajpa而是只使用了jooq所以需要用schema.sql和data.sql