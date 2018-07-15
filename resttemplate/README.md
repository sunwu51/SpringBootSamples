# RestTemplate的使用
```java
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

//通用形式(exchange) 常用于get方式设置header；put delete等其他请求
HttpHeaders headersx = new HttpHeaders();
headersx.set("Authorization","Bearer "+auth);
headersx.set("Cookie",cookie);
//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
MultiValueMap<String,String> mapx = new LinkedMultiValueMap<>();
HttpEntity requestEntityx = new HttpEntity(mapx, headersx);
ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, requestEntityx, String.class);

```