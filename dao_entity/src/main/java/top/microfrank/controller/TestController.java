package top.microfrank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.microfrank.dao.StudentDao;
import top.microfrank.domain.Student;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * Created by Frank Local on 2017/9/3.
 */
@RestController
@Transactional

public class TestController {
    @Autowired
    StudentDao studentDao;
    @RequestMapping("/test/{userid}")
    public ResponseEntity<Student> getStudent(@Pattern(regexp = "\\d+",message = "学生id应为数字") @PathVariable("userid") String userid){
        int id = Integer.parseInt(userid);
        return new ResponseEntity<Student>(studentDao.getOne(id), HttpStatus.OK);
    }

}
