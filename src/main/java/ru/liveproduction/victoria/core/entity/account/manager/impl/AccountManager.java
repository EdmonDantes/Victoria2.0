/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.manager.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.impl.Token;
import ru.liveproduction.victoria.core.entity.account.manager.IAccountManager;
import ru.liveproduction.victoria.core.entity.account.repository.AccountRepository;
import ru.liveproduction.victoria.core.entity.account.repository.TokenRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Singleton("account-manager")
@Transactional
public class AccountManager implements IAccountManager {

    private AccountRepository accountRepository;
    private TokenRepository tokenRepository;

    @Autowired
    public AccountManager(AccountRepository accountRepository, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
    }


    @Override
    @Transactional
    public @Nullable Account save(@NotNull Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Nullable
    public Token login(@NotNull String login, @NotNull String password) {
        Account account = accountRepository.findById(login.toLowerCase()).orElse(null);
        if (account == null || !account.getPassword().equals(password)) {
            return null;
        }

        Token token = new Token(RandomStringUtils.randomAlphanumeric(40));
        addToken(account, token);
        return token;
    }

    @Override
    @Transactional
    public @Nullable Account addToken(@NotNull Account account, @NotNull Token token) {
        token.setAccount(account);
        account.getTokens().add(token);
        return save(account);
    }

    @Override
    public @Nullable Account getFromToken(@NotNull String token) {
        Token accountToken = tokenRepository.findById(token).orElse(null);
        if (accountToken == null) {
            return null;
        }

        return accountToken.getAccount();
    }

    @Override
    public @Nullable Account getFromLogin(@NotNull String login) {
        return accountRepository.findById(login.toLowerCase()).orElse(null);
    }

    @Override
    public @Nullable Account addPoints(@NotNull Account account, @NotNull Integer points) {
        account.setScope(account.getScope() + points);
        return save(account);
    }
}
