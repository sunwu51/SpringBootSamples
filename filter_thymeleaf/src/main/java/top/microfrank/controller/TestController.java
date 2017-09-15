package top.microfrank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.microfrank.model.Clz;
import top.microfrank.model.Student;

import java.util.HashMap;

@Controller
public class TestController {

    //展现视图的两种写法
    //1 返回值直接是ModelAndView（如下）
    //2 返回值是String视图名，但是函数第一个参数是Model可追加属性

    //使用thymeleaf做渲染
    @RequestMapping("/hello")
    public ModelAndView hello(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("age",11);
        return new ModelAndView("hello",map);
    }

    //Rest接口直接返回字符串的写法
    @RequestMapping("/str")
    @ResponseBody
    public String str(){
        return "<h1>Hello</h1>";
    }

    //返回Json的方法
    //这里json的转换完全是用的jackson自动转换,对象内部的对象也会转换成json
    @RequestMapping("/json")
    @ResponseBody
    public ResponseEntity<Student> json(){
        Clz clz=new Clz();
        clz.setClzid(101);
        clz.setClazname("一班");
        Student student=new Student();
        student.setName("小明");
        student.setClz(clz);
        return new ResponseEntity<Student>(student,HttpStatus.OK);
    }


}
