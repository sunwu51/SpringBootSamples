package top.microfrank.actuatordemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Frank on 2018/1/4.
 */
@Controller
@RequestMapping("/")
public class WebController {
    @RequestMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<String>("index",HttpStatus.OK);
    }
    @RequestMapping("/a")
    public ResponseEntity<String> a(){
        return new ResponseEntity<String>("a",HttpStatus.OK);
    }
    @RequestMapping("/b")
    public ResponseEntity<String> b(){
        return new ResponseEntity<String>("b",HttpStatus.OK);
    }
}
