package com.com.backend.batch.processor;

import com.com.backend.model.Users;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UsersItemProcessor implements ItemProcessor<Users, Users> {

    @Override
    public Users process(Users users) throws Exception {
        return users;
    }

}
