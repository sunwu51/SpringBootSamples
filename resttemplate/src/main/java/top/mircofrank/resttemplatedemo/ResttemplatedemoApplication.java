package top.mircofrank.resttemplatedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class ResttemplatedemoApplication implements CommandLineRunner{
	@Autowired
	RestTemplate restTemplate;
	private  String url = "http://localhost:1880";
	@Bean
	public RestTemplate getRestClient(){
		//设置代理的方式
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
		requestFactory.setProxy(proxy);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(ResttemplatedemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//直接get请求url获得string参数
		String res1 = restTemplate.getForObject(url,String.class);

		//get请求中带querystring
		String res2 = restTemplate.getForObject(url+"?a=10&b=fdsaf",String.class);

		//占位符的方式填写get参数
		Map<String,String> map1 = new HashMap<>();
		map1.put("c","sdfa");
		String res4 = restTemplate.getForObject(url+"/get?a=10&b=fdsaf&c={c}",String.class,map1);

		//get方式传数组参数
		String res5 = restTemplate.getForObject(url+"/get?a=10&a=fdsa&a=dfa12",String.class);


		//post方式 不传参数直接请求服务端 默认的header中带有application/x-www-form-urlencoded
		String res6 = restTemplate.postForObject(url+"/post",null,String.class);

		//post方式 传递表单参数，因为默认就是表单提交所以header不用改动
		HttpHeaders headers = new HttpHeaders();
		Map<String,String> map2 = new HashMap<>();
		map2.put("c","sdfa");
		HttpEntity requestEntity = new HttpEntity(map2, headers);
		String res7 = restTemplate.postForObject(url+"/post",requestEntity,String.class);

		//post方式 传递表单同时，设置url中参数
		HttpHeaders headers1 = new HttpHeaders();
		Map<String,String> map3 = new HashMap<>();
		map3.put("c","sdfa");
		Map<String,String> map4 = new HashMap<>();
		map4.put("a","sdfa");
		HttpEntity requestEntity1 = new HttpEntity(map3, headers1);
		String res8 = restTemplate.postForObject(url+"/post?a={a}",requestEntity1,String.class,map4);

		//post方式 用MultiValueMap代替HashMap，两者大多数情况下效果一样，但是前者可以完成数组参数的提交如下
		HttpHeaders headers2 = new HttpHeaders();
		MultiValueMap<String, String> map5= new LinkedMultiValueMap<String, String>();
		map5.addAll("c", Arrays.asList("sdfa","fdsa"));
		HttpEntity requestEntity2 = new HttpEntity(map5, headers2);
		String res9 = restTemplate.postForObject(url+"/post",requestEntity2,String.class);


		//post方式 json格式数据而不是表单格式，只需要修改header
		HttpHeaders headers3 = new HttpHeaders();
		headers3.setContentType(MediaType.APPLICATION_JSON);
		MultiValueMap<String, String> map6= new LinkedMultiValueMap<String, String>();
		map6.addAll("c", Arrays.asList("sdfa","fdsa"));
		HttpEntity requestEntity3 = new HttpEntity(map6, headers3);
		String res10 = restTemplate.postForObject(url+"/post",requestEntity3,String.class);

	}


	public String getQueryString(MultiValueMap<String,String> params){
		StringBuilder s=new StringBuilder();
		params.forEach((k,list)->{
			list.forEach(it->{
				s.append(k).append("=").append(it).append("&");
			});
		});
		String str = s.toString();
		return str.endsWith("&")?str.substring(0,str.length()-1):str;
	}
}
