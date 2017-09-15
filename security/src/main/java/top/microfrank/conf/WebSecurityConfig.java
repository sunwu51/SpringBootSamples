package top.microfrank.conf;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

//
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    DataSource dataSource;
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//          auth.jdbcAuthentication().dataSource(dataSource);
        //上面这一行为直接通过Jdbc的方式进行用户auth配置，需要遵守一定的表结构，可以查看domain下两个类的结构

        //当然最灵活的方式是自定义UserDetailsService，实现这个接口即可//这里的自定义配置和上面的jdbc的方式最终效果是一摸一样的。
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()//对于请求的过滤，添加是否需要认证、需要角色的URL
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .and()
                .formLogin()//添加form登录方式默认配置了/login为登录页面可以在这重新配置，注意这里必须有username password两个字段提交到url，如果开启了csrf还有_csrf字段
                    .and()
                .httpBasic()//开启Basic认证方式
                    .and()
                .csrf()//csrf关闭
                    .disable()
                .headers()//设置响应头的安全类字段如x_frame_options还有下面的csp等
                    .frameOptions().sameOrigin()
                    .and()
                .logout()//设置logout页面，注意如果开启了csrf logout可能有点问题，可能是因为没有提交_csrf的原因
                    .logoutUrl("/logout")
                    .and()
                .headers()
                    .contentSecurityPolicy("script-src 'self' https://cdn.bootcss.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/")
        ;
    }

}