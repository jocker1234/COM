package com.com.backend.service.serviceImpl;

import com.com.backend.dao.AbstractsDao;
import com.com.backend.exception.AppException;
import com.com.backend.model.Abstracts;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.service.AbstractsAbstractService;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbstractServiceImpl implements AbstractsService {

    private UsersService usersService;
    private AbstractsAbstractService researchAbstract;
    private AbstractsAbstractService caseAbstract;
    private AbstractsDao abstractsDao;

    public AbstractServiceImpl(UsersService usersService,
                               @Lazy @Qualifier("researchAbstractsServiceImpl") AbstractsAbstractService researchAbstract,
                               @Lazy @Qualifier("caseAbstractsServiceImpl") AbstractsAbstractService caseAbstract,
                               AbstractsDao abstractsDao) {
        this.usersService = usersService;
        this.researchAbstract = researchAbstract;
        this.caseAbstract = caseAbstract;
        this.abstractsDao = abstractsDao;
    }

    public List<Abstracts> getAllAbstractUser(String token) {
        String email = usersService.getEmailFromToken(token);
        List<Abstracts> abstracts = new ArrayList<>();
        abstracts.addAll(caseAbstract.getAllAbstractsByUserEmail(email));
        abstracts.addAll(researchAbstract.getAllAbstractsByUserEmail(email));
        return abstracts;
    }

    @Override
    public long countAllAbstractUser(String email) throws AppException {
        long id = usersService.getUserIdByEmail(email);
        long count = abstractsDao.countAllAbstracts(id);
        if(count >= 2)
            throw new AppException(ExceptionType.ABSTRACT_AMOUNT);
        return count;
    }

}
