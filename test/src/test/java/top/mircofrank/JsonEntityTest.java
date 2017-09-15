package top.mircofrank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Frank Local on 2017/9/15.
 */
@RunWith(SpringRunner.class)
@JsonTest
/**
 * jsontest是来测试json和类之间转换是否正确的，并不会走字段校验，所以其实用处不大
 */

public class JsonEntityTest {
    /**
     * JacksonTester用来测试某一个类转json字符串，这里idea报没有这个bean不用理会
     */
    @Autowired
    private JacksonTester<MyEntity> json;
    @Test
    public void serializeJson() throws Exception {
        MyEntity details = new MyEntity();
        details.setAge(10);
        details.setId(1);
        details.setEmail("sss");
        details.setPassword("213123213");
        details.setPhone("2234324324");
        System.out.println(this.json.write(details).getJson());
    }

    @Test
    public void deserializeJson() throws Exception {

        String content = "{\"id\":1,\"password\":\"213123213\",\"age\":10,\"phone\":\"2234324324\",\"email\":\"sss\"}";

        System.out.println(this.json.parse(content).getObject().getPhone());
    }


}
