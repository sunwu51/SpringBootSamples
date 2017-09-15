package top.mircofrank;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Frank Local on 2017/9/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
/**
 * 专门才是springdata jpa的，会导入import.sql
 * 可以注入jpa repository进行测试
 */
public class JpaDealTest {
    @Autowired
    MyEntityRepository myEntityRepository;

    @Test
    @Transactional
//    @Rollback(false)//在加了事务注解的情况下添加Rollback为false则可以在测试环境下也进行数据库操作而不回滚
    /*
     * 事务注解默认在测试环境下会回滚，因而这里的age还是原来的20
     * 在直接用Repository操作数据的时候加和不加@Transactional是一样的
     * 但是如果是Service层的或者Controller的话，则不加会导致Hibernate session丢失而失败，所以一般还是加上
     */
    public void addEntity(){
        MyEntity entity=new MyEntity();
        entity.setId(1);
        entity.setAge(10);
        myEntityRepository.save(entity);
    }

    @Test
    public void getById(){
        Assert.assertEquals(myEntityRepository.getOne(1).getAge(),20);
    }

}

