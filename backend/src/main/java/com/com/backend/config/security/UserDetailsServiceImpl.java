package com.com.backend.config.security;

import com.com.backend.dao.UsersDao;
import com.com.backend.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImp")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersDao usersDao;

    public UserDetailsServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> email: " + email));
        return UserPrinciple.build(user);
    }

}

