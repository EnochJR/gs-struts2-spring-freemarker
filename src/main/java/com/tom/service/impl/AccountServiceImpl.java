package com.tom.service.impl;

import com.tom.dao.AccountDao;
import com.tom.model.Account;
import com.tom.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * User: TOM
 * Date: 2016/5/13
 * email: beauty9235@gmail.com
 * Time: 16:43
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public Account queryAccountByUsername(String username) {
        Account account=new Account();
        account.setUsername(username);
        return accountDao.queryOneAccount(account);
    }
}
