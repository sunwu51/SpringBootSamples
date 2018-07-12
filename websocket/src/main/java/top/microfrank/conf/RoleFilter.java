package top.microfrank.conf;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Frank Local on 2017/7/25.
 */

/**
 * 权限过滤器，验证Token合法性
 */
public class RoleFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authstr = request.getRequestURI().startsWith("/endpointSang/info") ? request.getParameter("Authorization") : request.getHeader("Authorization");
        if (authstr == null || !authstr.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        String auth = authstr.substring(7);
        System.out.println(auth);
//      这里是采用jwt的时候做token合法认证的代码
//      if(!auth.isValid()){
//        chain.doFilter(request, response);
//      }
        UserDetails userDetails=new JuserDetails(null,null, AuthorityUtils.createAuthorityList("ROLE_TEST") );
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,  userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}