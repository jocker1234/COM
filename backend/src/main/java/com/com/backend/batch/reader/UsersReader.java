package com.com.backend.batch.reader;

import com.com.backend.dao.UsersDao;
import com.com.backend.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@StepScope
public class UsersReader extends RepositoryItemReader<Users> {

    private UsersDao usersDao;

    public UsersReader(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @PostConstruct
    public void init() {
        log.info("Start reading");
        setRepository(usersDao);
        setSort(sortByIdAsc());
        setMethodName("findAll");
    }

    private Map<String, Sort.Direction> sortByIdAsc() {
        Map<String, Sort.Direction> sortConfiguration = new HashMap<>();
        sortConfiguration.put("id", Sort.Direction.ASC);
        return sortConfiguration;
    }

}
