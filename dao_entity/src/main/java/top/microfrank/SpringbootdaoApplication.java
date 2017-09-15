package top.microfrank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.microfrank.dao.ClazDao;
import top.microfrank.dao.StudentDao;
import top.microfrank.domain.Claz;
import top.microfrank.domain.Student;

@SpringBootApplication
public class SpringbootdaoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdaoApplication.class, args);
	}

	@Autowired
	ClazDao clazDao;
	@Autowired
	StudentDao studentDao;
	@Override
	public void run(String... strings) throws Exception {
		//claz表插入10个班级
		for(int i=1;i<11;i++){
			Claz claz=new Claz();
			claz.setCid(i);
			claz.setCname(i+"班");
			clazDao.save(claz);
		}
		//插入一个学生
		Student student=new Student();
		student.setSid(1);
		student.setName("frank");
		student.setClaz(clazDao.getOne(1));
		studentDao.save(student);

	}
}
