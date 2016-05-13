package com.tom.dao.impl;

import com.tom.dao.AccountDao;
import com.tom.model.Account;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * User: TOM
 * Date: 2016/5/13
 * email: beauty9235@gmail.com
 * Time: 16:49
 */
public class AccountDaoImpl extends SqlMapClientDaoSupport implements AccountDao {
    @Override
    public Account queryOneAccount(Account account) {
        return (Account) getSqlMapClientTemplate().queryForObject("queryOneAccount", account);
    }
}
