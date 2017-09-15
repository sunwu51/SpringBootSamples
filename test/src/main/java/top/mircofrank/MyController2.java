package top.mircofrank;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Frank Local on 2017/9/15.
 */
@Controller
public class MyController2 {
    @RequestMapping("/c2")
    @ResponseBody
    public String index(){
        return "hello c2";
    }
}