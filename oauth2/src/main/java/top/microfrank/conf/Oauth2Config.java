package top.microfrank.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by Frank Local on 2017/9/12.
 * 这是Oauth2的配置类，其实是分别写 权限管服务理配置适配器 和 资源服务器是配置
 * Oauth2中第三方应用在客户允许情况下，会向Oauth提供方请求申请access_token，这就需要权限管理器【提供和管理token】
 * 第三方拿到access_token后，会用这个token来请求我们站点的用户相关数据
 *
 * 一般情况下是这样的，我们作为Oauth提供方，会事先定好几种client，比如只能查看头像和昵称的client 以及 还可以查看个人照片的client
 * 第三方申请合作后我们给他这个clientid和secret，并告诉他们我们这里可以获取access_token的方式【最常见的就是password、authorization_code和refresh_token】
 * 他们通过相应的方式在客户许可下获取到access_token
 * access_token以Bearer认证的形式就可以请求相应的接口，当然这些接口我们也要事先告诉第三方
 * 如果第三方请求了其他接口，则不被允许。这得益于首先Spring Security中Role或者叫Authority的配置，同时还有Oauth2的scope
 * 这两者任意一个不符合条件都不能请求到
 *
 * 以虎牙登录可以选择qq方式登录为例子，qq服务器就是权限管理服务器+资源服务器
 * 用户点击登录，找到qq方式登录然后通过qq的一个内嵌的页面输入账密登录后，点击授权
 * 此时qq产生了一个authorization_code到页面上，跳转的时候虎牙通过这个code请求qq的权限服务器获取到了access_token
 * 然后就把这个token和这个用户相关的信息存了起来，之后通过token获取用户头像昵称，其实获取access_token同时还有refresh_token和超时时间(s)
 * 2.0token有了时效，如果下次请求头像数据的时候发现access_token超时了，就可以用refresh_token再次请求一个新的access_token而不用用户再次点授权
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2Config {
    @EnableAuthorizationServer
    @Configuration
    public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
        @Autowired
        UserDetailsService userDetailsService; // 引入security中提供的 UserDetailsService
        @Autowired
        AuthenticationManager authenticationManager; // 引入security中提供的 AuthenticationManager

        /**
         * 配置oauth的/oauth/token_key和/oauth/check_token页面的规则，默认是禁止被访问
         * 由框架提供的URL路径/oauth/authorize（授权端点）/oauth/token（令牌端点）/oauth/confirm_access（用户发布批准此处）
         * /oauth/error（用于在授权服务器中呈现错误）/oauth/check_token（由资源服务器用于解码访问令牌） ，
         * 并且/oauth/token_key（如果使用JWT令牌，则公开用于令牌验证的公钥）。
         *
         * @param security
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("hasRole('USER')")
            ;
        }

        /**
         * 配置clients属性，这里直接配置在内存的变量里了，也可以选择jdbc的方式
         *
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("client_id") // 配置默认的client
                    .secret("client_secret")
                    .authorizedGrantTypes("password", "authorization_code", "client_credentials", "refresh_token")
                    .scopes("read").autoApprove("read").accessTokenValiditySeconds(60 * 11).authorities("fly")
                    .and()
                    .withClient("c")
                    .secret("s")
                    .authorizedGrantTypes("password", "authorization_code", "client_credentials", "refresh_token")
                    .scopes("fuk", "read").autoApprove("fuk").accessTokenValiditySeconds(60 * 11)
            ;

        }

        /**
         * 授权的详细信息，包括token存储位置，UserDetailsService等
         *
         * @param endpoints
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Bean
        public TokenStore tokenStore(){
            return new InMemoryTokenStore(); //使用内存中的 token store
        }
    }

    /**
     * 资源访问控制，这里注意如果同时配置了websecurityconfig，则里面需要认证的在oauth中也得认证，
     * 而且两者共存的时候login页面失效，basic认证也失效，建议不要一起用
     */
    @EnableResourceServer
    @Configuration
    public class ResourceConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            //在这里配置scope规则和在控制器方法上加注解效果一样，下面代码等价于TestController48行注释的部分
            http.
                    authorizeRequests()
                    .antMatchers("/").access("#oauth2.hasScope('fuk3')")
                    .anyRequest().permitAll()
            ;
        }
    }

}
