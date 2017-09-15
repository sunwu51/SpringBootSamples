package top.mircofrank;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Frank Local on 2017/9/16.
 */
@RunWith(SpringRunner.class)
@RestClientTest
/**
 * 这个一般注解是在写客户端测试程序的时候才会使用，与下面的restTemplate配合使用
 * 请求的url不能是本程序的，因为这个测试运行的时候没有启动Controller
 */
public class ClientTest {
    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test(){
        MyEntity entity = restTemplate.getForEntity("http://baidu.com",MyEntity.class).getBody();
        Assert.assertEquals(entity.getPhone(),"110");
    }
}
