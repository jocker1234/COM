package com.com.backend.batch.reader;

import com.com.backend.batch.StringHeaderWriter;
import com.com.backend.dao.UsersDao;
import com.com.backend.model.Users;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

@Component
public class UsersReader extends RepositoryItemReader<Users> {

    private UsersDao usersDao;

    public UsersReader(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @PostConstruct
    public void init() {
        setRepository(usersDao);
        setSort(sortByIdAsc());
        setMethodName("findAll");
        //setPageSize(1);
        //setRowMapper(new BeanPropertyRowMapper<>(Users.class));
        //PagingQueryProvider pagingQueryProvider = createQueryProvider();
        //setQueryProvider(pagingQueryProvider);
    }

    /*private PagingQueryProvider createQueryProvider() {
        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();

        queryProvider.setSelectClause("SELECT id, email");
        queryProvider.setFromClause("FROM users");
        queryProvider.setSortKeys(sortByEmailAddressAsc());
        return queryProvider;
    }*/

    private Map<String, Sort.Direction> sortByIdAsc() {
        Map<String, Sort.Direction> sortConfiguration = new HashMap<>();
        sortConfiguration.put("id", Sort.Direction.ASC);
        return sortConfiguration;
    }

}
