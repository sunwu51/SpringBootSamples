# 请求参数的解析
```java
// 1 逐一将各个参数作为函数参数的形式
// get请求可以匹配 ?username=xxx&password=xxx&age=xxx|||出错情况：age字段缺少会将null转int，此时出错。age传了但是不能转int也出错
// post请求可以匹配 必须是form格式的提交|||出错情况：同上
// 想要get或post且字段都是必须的设定，则可以参考下面的@RequestParam注解
@RequestMapping("/p1")
public ResponseEntity<Map> p1(String username, String password, int age){
    Map<String,Object> map = new HashMap<>();
    map.put("username",username);
    map.put("passwd",password);
    map.put("age",age);
    return  new ResponseEntity<Map>(map, HttpStatus.OK);
}

// 1 逐一模式增强版
// @RequestParam注解：value实际提交的字段key，required是否必须，如果false则可以或缺该参数，defaultValue如果没有提交默认填充值
// @Valid/@Validated注解：加在类上，字段的验证，如字符串不能为空，最小长度，数字的最大最小值，正则等等【注意一定要在类上加valid/validated注解】
@RequestMapping("/p2")
public ResponseEntity<Map> p2(@RequestParam(value = "u",required = false,defaultValue = "uuu") String username, @Size(min = 6)@Pattern(regexp = "\\d+") String password, int age){
    Map<String,Object> map = new HashMap<>();
    map.put("username",username);
    map.put("passwd",password);
    map.put("age",age);
    return  new ResponseEntity<Map>(map, HttpStatus.OK);
}

// 2 类参数模式
// 参数是一个封好的类，上传的参数是类的各个字段，运行原理为new一个对象，然后根据上传参数字段set进去
// 如果参数中没有类中字段，则为默认值，因为没有set；如果参数中有冗余字段，则被忽略
@RequestMapping("/p3")
public ResponseEntity<User> p3(User user){
    return  new ResponseEntity<User>(user, HttpStatus.OK);
}

// 2 类参数模式加强版
// 对于每个字段的校验和设置，放到User类中字段上面；如果是检验，这里需要在参数前加@Valid/@Validated。这和之前的逐一格式不一样，之前是加在类上，这里必须加在参数前
@RequestMapping("/p4")
public ResponseEntity<User> p4(@Validated User user){
    return  new ResponseEntity<User>(user, HttpStatus.OK);
}

// 3 1-2混合型
@RequestMapping("/p5")
public ResponseEntity<User> p5(@Validated User user,@Size(min=3)@RequestParam String str){
    System.out.println(str);
    return  new ResponseEntity<User>(user, HttpStatus.OK);
}

// 4 json格式
// @RequestBody注解可以解析json格式，该注解只允许application/json格式，其他格式都返回错误提示
@RequestMapping("/p6")
public ResponseEntity<User> p6(@RequestBody @Validated  User user){
    return  new ResponseEntity<User>(user, HttpStatus.OK);
}

// 4 json格式加强版
// json只能post提交，在post的时候如果url中还带有参数如?str=xxx，可以通过该混合模式获取str
@RequestMapping("/p7")
public ResponseEntity<User> p7(@RequestBody @Validated  User user,@Size(min=3)@RequestParam String str){
    System.out.println(str);
    return  new ResponseEntity<User>(user, HttpStatus.OK);
}
// 5 路径参数
//{}表示是路径参数，参数中添加@PathVariable注解即可
@RequestMapping("/p8/{str}")
public ResponseEntity<User> p8(@RequestBody @Validated  User user,@Size(min=3)@PathVariable String str){
    System.out.println(str);
    return  new ResponseEntity<User>(user, HttpStatus.OK);
}

// 1 高级参数
// header参数获取
@RequestMapping("/h1")
public ResponseEntity<String> h1(@RequestHeader(value = "Authorization",required =false ) @Pattern(regexp = "Bearer \\w+",message = "Authorization格式错误") String auth){
    return  new ResponseEntity<String>("OK", HttpStatus.OK);
}
// cookie参数获取
@RequestMapping("/h2")
public ResponseEntity<String> h2(@CookieValue(required = false)String foo){
    return  new ResponseEntity<String>("OK", HttpStatus.OK);
}
// 数组参数
// 1 如果按照string接收数组参数，则为xx,xx,xxx 这种逗号隔开的字符串
@RequestMapping("/h3")
public ResponseEntity<String> h3(String foo){
    System.out.println(foo);
    return  new ResponseEntity<String>(foo, HttpStatus.OK);
}
// 2 直接用数组格式的参数就可以接收数组参数
// 注：有些前端框架post数组参数时的key是foo[]有些是foo，根据实际情况改这个value就好了
@RequestMapping("/h4")
public ResponseEntity<String> h4(@RequestParam("foo") String[] foo){
    return  new ResponseEntity<String>(Arrays.toString(foo), HttpStatus.OK);
}
```