/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.api.rest.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.impl.Token;
import ru.liveproduction.victoria.core.entity.account.manager.IAccountManager;

@RestController
public class AccountRestController {

    private IAccountManager accountManager;

    @Autowired
    public AccountRestController(IAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/account/register")
    @ResponseBody
    public Account registerAccount(@RequestParam String login, @RequestParam String password) {
        Account tmp = accountManager.getFromLogin(login.toLowerCase());
        if (tmp != null) {
            throw new RuntimeException("Login already used. Please chose another.");
        }

        return accountManager.save(new Account(login, password));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/account/login")
    @ResponseBody
    public Token loginAccount(@RequestParam String login, @RequestParam String password) {
        Token result = accountManager.login(login, password);
        if (result == null) {
            throw new RuntimeException("Wrong login or password.");
        }

        return result;
    }

}
