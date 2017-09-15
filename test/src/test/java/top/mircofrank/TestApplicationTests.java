package top.mircofrank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestApplicationTests {
	/**
	 * 可以自动注入spring的bean
	 */
	@Autowired
	MyBean generalBean;

	/**
	 * 也可以自己创建MockBean进行测试 因为bean是单例的，这里mock之后把上面的也改了
	 */
	@MockBean
	MyBean generalBean2;
	@Before
	public void setup() {
		given(generalBean2.getP1()).willReturn("hahaha");
	}


	@Test
	public void contextLoads() {
		System.out.println(generalBean.getP1());//mock改动了单例这个也随之改变
		System.out.println(generalBean2.getP1());
	}

}
