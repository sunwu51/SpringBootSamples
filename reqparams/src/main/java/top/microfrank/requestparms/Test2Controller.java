package top.microfrank.requestparms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Frank on 2018/7/15.
 */
@Controller
@Validated
public class Test2Controller {

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


}
