package top.microfrank.conf;

import org.springframework.data.jpa.repository.JpaRepository;
import top.microfrank.domain.Authorities;

import java.util.List;

/**
 * Created by Frank Local on 2017/9/9.
 */
public interface AuthoritiesDao extends JpaRepository<Authorities,String>{
    List<Authorities> getAllByUsername(String username);
}
