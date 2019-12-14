package com.com.backend.batch.processor;

import com.com.backend.model.Users;
import com.com.backend.model.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsersItemProcessor implements ItemProcessor<Users, Users> {

    @Override
    public Users process(Users users) throws Exception {
        if(!users.getAuthoritiesSet().stream().anyMatch(users1 -> users1.getRoleName().equals(Role.ROLE_ADMIN))) {
            log.info("Processing result: " + users);
            return users;
        } else {
            return null;
        }
    }

}
