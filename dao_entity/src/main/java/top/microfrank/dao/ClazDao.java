package top.microfrank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import top.microfrank.domain.Claz;

@RepositoryRestResource(collectionResourceRel ="class",path = "class")
public interface ClazDao extends JpaRepository<Claz,Integer> {
    Claz getClazByCname(String cname);
}
