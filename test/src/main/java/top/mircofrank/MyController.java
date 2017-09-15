package top.mircofrank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

/**
 * Created by Frank Local on 2017/9/15.
 */
@Controller
public class MyController {
    @RequestMapping("/c1")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/entity/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MyEntity> getEntity(@PathVariable("id")int id){
        MyEntity entity=new MyEntity();
        entity.setAge(12);
        entity.setId(id);
        entity.setPhone("110");
        return new ResponseEntity<MyEntity>(entity, HttpStatus.OK);
    }
}
