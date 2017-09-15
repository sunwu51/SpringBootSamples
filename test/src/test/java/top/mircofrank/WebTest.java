package top.mircofrank;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Frank Local on 2017/9/15.
 */
@RunWith(SpringRunner.class)
@WebMvcTest({MyController.class,MyController2.class})

//注意这个WebMvcTest注解不加参数就是所有的controller都能测试
// ，加了则只测试加的控制器，其他控制器中的url映射无效

public class WebTest {
    @Autowired
    private MockMvc mvc;

    /**
     * 利用spring test框架中的mockmvc这个bean测试web层
     * 在spring boot的webmvctest注解下可以自动注入
     * 如下：按照文本来接收/c1,期望返回状态200，返回内容含有hello c1字样
     * @throws Exception
     */
    @Test
    public void getPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/c1").accept(MediaType.TEXT_HTML_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hehe")));
        mvc.perform(MockMvcRequestBuilders.get("/c2").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hello")));
    }
}
