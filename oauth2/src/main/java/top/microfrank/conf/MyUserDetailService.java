package top.microfrank.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.microfrank.domain.Authorities;

/**
 * Created by Frank Local on 2017/9/9.
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UsersDao usersDao;
    @Autowired
    AuthoritiesDao authoritiesDao;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUserDetails userDetails=usersDao.getOne(s);
        userDetails.setAuthorities(MyUserDetails.mapToGrantedAuthorities(authoritiesDao.getAllByUsername(s)));
        return userDetails;
    }
}
