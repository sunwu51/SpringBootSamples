# thymeleaf and filter
## thymeleaf
默认支持的模板，只需引入依赖即可在resource/templates文件夹写视图。
## filter
两种生效方式：
- 实现Filter接口，注入为Bean  
- 注入一个FilterRegisterationBean为Bean，其中设置了Filter Url-Pattern和Order

*本例子中设置了三个filter，其中TestFilter通过方法1成为过滤器，FilterOne和FilterTwo则是通过方法2，并配置了顺序是Two->One*
## 运行
这个样例只需要`mvn spring-boot:run`即可运行