package com.tom.service;

import com.tom.model.Account;

/**
 * User: TOM
 * Date: 2016/5/13
 * email: beauty9235@gmail.com
 * Time: 16:42
 */
public interface AccountService {
    Account queryAccountByUsername(String username);

}
