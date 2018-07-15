package top.microfrank.requestparms;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Frank on 2018/7/15.
 */
public class Test2ControllerTest {
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
    private void request(String auth,String cookie){
//        //  get 方式提交参数
//        String res = restTemplate.getForObject(url+path+"?username={username}&password={password}&age={age}",String.class,username,password,age);
//        log.info("p1:get:"+res);


        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+auth);
        headers.set("Cookie",cookie);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        HttpEntity requestEntity = new HttpEntity(map, headers);
//        String res = restTemplate.postForObject(url+path,requestEntity,String.class);
        ResponseEntity<String> res = restTemplate.exchange(url+path, HttpMethod.GET, requestEntity, String.class);
        log.info("p1:post:"+res.getBody());
        log.info("============================================================");
    }
    private void requestArray(String[] arr){
//        //  get 方式提交参数
//        String res = restTemplate.getForObject(url+path+"?username={username}&password={password}&age={age}",String.class,username,password,age);
//        log.info("p1:get:"+res);

        // post 方式提交参数
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.addAll("foo", Arrays.asList(arr));
        HttpEntity requestEntity = new HttpEntity(map, headers);
        String res = restTemplate.postForObject(url+path,requestEntity,String.class);
        log.info("p1:post:"+res);
        log.info("============================================================");
    }
    @Test
    public void h1() throws Exception {
        path="h1";
        request("test","");

    }

    @Test
    public void h2() throws Exception {
        path = "h2";
        request("test","foo=bar; a=b");
    }

    @Test
    public void h3() throws Exception {
        path="h3";
        requestArray(new String[]{"a","b","c"});
        //服务端打印 a,b,c
    }

    @Test
    public void h4() throws Exception {
        path="h4";
        requestArray(new String[]{"a","b","c"});
        //服务端打印正确接收数组类型
    }

}