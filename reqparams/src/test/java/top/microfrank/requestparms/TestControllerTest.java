package top.microfrank.requestparms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Frank on 2018/7/15.
 */
public class TestControllerTest {
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";
    private Logger log;
    private String path;
    @Before
    public void init(){
        //设置代理的方式
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
        requestFactory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        this.restTemplate=restTemplate;
        this.log = LoggerFactory.getLogger(this.getClass());
    }
    private void request(String username,String password,int age){
        //  get 方式提交参数
        String res = restTemplate.getForObject(url+path+"?username={username}&password={password}&age={age}",String.class,username,password,age);
        log.info("p1:get:"+res);

        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("username",username);
        map.add("password",password);
        map.add("age",age+"");
        HttpEntity requestEntity = new HttpEntity(map, headers);
        res = restTemplate.postForObject(url+path,requestEntity,String.class);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }
    private void request(String username,String password,int age,String str){
        //  get 方式提交参数
        String res = restTemplate.getForObject(url+path+"?username={username}&password={password}&age={age}&str={str}",String.class,username,password,age,str);
        log.info("p1:get:"+res);

        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("username",username);
        map.add("password",password);
        map.add("str",str);
        map.add("age",age+"");
        HttpEntity requestEntity = new HttpEntity(map, headers);
        res = restTemplate.postForObject(url+path,requestEntity,String.class);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }
    private void request(String password,int age){
        //  get 方式提交参数
        String res = restTemplate.getForObject(url+path+"?password={password}&age={age}",String.class,password,age);
        log.info("p1:get:"+res);

        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("password",password);
        map.add("age",age+"");
        HttpEntity requestEntity = new HttpEntity(map, headers);
        res = restTemplate.postForObject(url+path,requestEntity,String.class);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }
    private void request(String username,String password){
        //  get 方式提交参数
        String res = restTemplate.getForObject(url+path+"?username={username}&password={password}",String.class,username,password);
        log.info("p1:get:"+res);

        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("username",username);
        map.add("password",password);
        HttpEntity requestEntity = new HttpEntity(map, headers);
        res = restTemplate.postForObject(url+path,requestEntity,String.class);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }

    private void requestjson(String username,String password,int age){
        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("age",age+"");
        HttpEntity requestEntity = new HttpEntity(map, headers);
        String res = restTemplate.postForObject(url+path,requestEntity,String.class);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }
    private void requestjson(String username,String password,int age,String str){
        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("age",age+"");
        HttpEntity requestEntity = new HttpEntity(map, headers);
        String res = restTemplate.postForObject(url+path+"?str={str}",requestEntity,String.class,str);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }


    @Test
    public void p1() throws Exception {
        path="p1";
        request("u","p",1);
        //{"passwd":"p","age":1,"username":"u"}

        request("p",1);//缺少username字段，会自动填充为null
        //{"passwd":"p","age":1,"username":null}


        try {
            request("u","p");//缺少age字段，因为null转int失败，返回500错误
            throw new Exception("other error");
        }catch (HttpServerErrorException e){

        }
    }

    @Test
    public void p2() throws Exception {
        path="p2";
        request("33333333333",1);
        //{"passwd":"33333333333","age":1,"username":"uuu"}
        //缺少username字段，会自动填充为服务端defaultValue

        //password在服务端设置了长度min为6，且正则\\d+，所以应为长度大于6的数字形式，其他形式会报错.下面两种都会报错
//        request("adfdsafsadf",1);
//        request("1",1);

    }

    @Test
    public void p3() throws Exception {
        path="p3";
        request("u","p",1);
        //{"username":"u","password":"p","age":1}

        request("p",1);//缺少username字段，会自动填充为null
        //{"username":null,"password":"p","age":1}

        request("u","p"); //缺少age，自动填充为0
        //{"username":"u","password":"p","age":0}

        //对比p1，发现在类参数形式的int参数不传，自动赋值0。逐一参数的则是null，并在转换时报错
    }

    @Test
    public void p4() throws Exception {
        path="p4";
        request("u","4444444",55);
        //{"username":"u","password":"4444444","age":55}

        //request("u","2",5);  //错误：服务端设置了password的正则和age的最小值是45

        //request("4444444",55);// 错误：服务端设置了username不能为null

        //request("u","p");   //错误：服务端设置了age的最小是45，此时为0

    }

    @Test
    public void p5() throws Exception {
        path="p5";
        request("u","1111111111",66,"1234");
        //{"username":"u","password":"1111111111","age":66}
        //服务端打印：1234
    }

    @Test
    public void p6() throws Exception {
        path="p6";
        requestjson("u","11111111",55);
        //{"username":"u","password":"11111111","age":55}
    }

    @Test
    public void p7() throws Exception {
        path="p7";
        requestjson("u","11111111",66,"hahaha");
        //{"username":"u","password":"11111111","age":66}
        //服务端打印hahaha
    }

    @Test
    public void p8() throws Exception {
        path="/p8/haha";
        requestjson("u","11111111",55);
        //{"username":"u","password":"11111111","age":55}
        //服务端打印：haha
    }

}