package top.microfrank.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Frank Local on 2017/9/3.
 */
@Controller
public class TestController {
    @Autowired
    SecurityManager securityManager;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password, Model model){
        AuthenticationToken token=new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            return "redirect:/";
        }catch (Exception e){
            model.addAttribute("alert","用户名或密码错误");
        }
        return "login";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "<h1>退出登录成功<h1>";
    }
    //登陆可以看的
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "<h1>登录用户就可以看到</h1>" +
                "<a href='/role/guest'>[Role]guest</a><br>"+
                "<a href='/role/general'>[Role]general</a><br>"+
                "<a href='/role/admin'>[Role]admin</a><br>"+
                "<a href='/role/guestandgeneral'>[Role]guest并且general</a><br>"+
                "<a href='/permission/select'>[permission]select</a><br>"+
                "<a href='/permission/delete'>[permission]delete</a><br>"+
                "<a href='/permission/insert'>[permission]insert</a><br>"+
                "<a href='/permission/update'>[permission]update</a><br>"+
                "<a href='/permission/selectandupdate'>[permission]select并且update</a><br>"
                ;
    }


    //角色控制
    @RequestMapping("/role/admin")
    @RequiresRoles("admin")
    @ResponseBody
    public String admin(){
        return "<h1>管理员【角色】能看到</h1>";
    }
    @RequestMapping("/role/general")
    @RequiresRoles("general")
    @ResponseBody
    public String general(){
        return "<h1>普通用户【角色】能看到</h1>";
    }
    @RequestMapping("/role/guest")
    @RequiresRoles("guest")
    @ResponseBody
    public String guest(){
        return "<h1>游客【角色】能看到</h1>";
    }
    @RequestMapping("/role/guestandgeneral")
    @RequiresRoles({"guest","general"})
    @ResponseBody
    public String guestAndGeneral(){
        return "<h1>既是游客又是普通用户【角色】能看到</h1>";
    }


    @RequestMapping("/permission/insert")
    @RequiresPermissions("insert")
    @ResponseBody
    public String insert(){
        return "<h1>增【权限】才能看到</h1>";
    }
    @RequestMapping("/permission/delete")
    @RequiresPermissions("delete")
    @ResponseBody
    public String delete(){
        return "<h1>删【权限】才能看到</h1>";
    }
    @RequestMapping("/permission/update")
    @RequiresPermissions("update")
    @ResponseBody
    public String update(){
        return "<h1>改【权限】才能看到</h1>";
    }
    @RequestMapping("/permission/select")
    @RequiresPermissions("select")
    @ResponseBody
    public String select(){
        return "<h1>查【权限】才能看到</h1>";
    }
    @RequestMapping("/permission/selectandupdate")
    @RequiresPermissions({"select","update"})
    @ResponseBody
    public String selectAndUpdate(){
        return "<h1>查和改【权限】才能看到</h1>";
    }

}
