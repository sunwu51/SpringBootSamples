package top.microfrank.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 * FilterRegestrationBean也可以注入Filter，并且可以设置order和url-pattern
 */
@Configuration
public class FilterRegistration{
    @Bean
    public FilterRegistrationBean getFR(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new FilterOne());
        registrationBean.setOrder(2);//order数字越小优先级越高
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean getFR2(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new FilterTwo());
        registrationBean.setOrder(1);//order数字越小优先级越高
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
