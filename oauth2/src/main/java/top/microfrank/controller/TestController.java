package top.microfrank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Frank Local on 2017/9/3.
 */
@Controller
public class TestController {


    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());;
        return "<h1>登录用户就可以看到</h1>";
    }
    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String admin(){
        return "<h1>管理员【角色】才能看到</h1>";
    }


    @RequestMapping("/fly")
    @ResponseBody
    @PreAuthorize("hasRole('FLY')")//&& #oauth2.hasScope('fuk3')"
    public String fly(){
        return "<h1>fly【角色】才能看到</h1>";
    }

}
