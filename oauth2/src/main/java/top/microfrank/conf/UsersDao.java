package top.microfrank.conf;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Frank Local on 2017/9/9.
 */
public interface UsersDao extends JpaRepository<MyUserDetails,String>{
}
