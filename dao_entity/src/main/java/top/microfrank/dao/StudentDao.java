package top.microfrank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import top.microfrank.domain.Student;

@RepositoryRestResource(collectionResourceRel ="student",path = "student")
public interface StudentDao extends JpaRepository<Student,Integer> {
}
