package top.microfrank.filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 最简单的过滤器只需要实现Filter接口，并写个Component注解
 * 不足之处：
 * 1 如果有多个Filter的时候，过滤的顺序没法确定（从debug信息上看好像是按照命名排序的）
 * 2 设置过滤路径的时候需要自己判断
 * 如果有多个过滤器则建议使用FilterRegestration来配置，其可设置order和url-pattern
 */
@Component
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if(httpRequest.getRequestURI().contains("str")){
            System.out.println("该路径含有str字段被过滤掉啦！");
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.getWriter().write("{\"state\":0,\"info\":\"该路径含有str字段被过滤掉啦！\"}");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
