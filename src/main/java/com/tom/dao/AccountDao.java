package com.tom.dao;

import com.tom.model.Account;

/**
 * User: TOM
 * Date: 2016/5/13
 * email: beauty9235@gmail.com
 * Time: 16:48
 */
public interface AccountDao {
    Account queryOneAccount(Account account);
}
