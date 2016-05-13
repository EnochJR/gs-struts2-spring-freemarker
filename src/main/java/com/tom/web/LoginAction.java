package com.tom.web;

import com.tom.filter.WebConstants;
import com.tom.model.Account;
import com.tom.service.AccountService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

/**
 * User: TOM
 * Date: 2016/5/13
 * email: beauty9235@gmail.com
 * Time: 14:25
 */
public class LoginAction extends BaseAction {
    Account account;
    boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Autowired
    AccountService accountService;

    public String execute() throws Exception {
        if (account != null) {
            Account _account=accountService.queryAccountByUsername(account.getUsername());
            if (StringUtils.equals(account.getUsername(), "tomLuo") && StringUtils.equals(account.getPassword(), "tomLuo")) {
                WebUtils.setSessionAttribute(request, WebConstants.SESSION_CURRENT_USER, account);
                return SUCCESS;
            } else {
                return ERROR;
            }
        }
        return INPUT;
    }
}
