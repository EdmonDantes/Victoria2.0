/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.impl.Token;
import ru.liveproduction.victoria.core.entity.account.manager.IAccountManager;
import ru.liveproduction.victoria.core.entity.account.repository.AccountRepository;
import ru.liveproduction.victoria.core.entity.account.repository.TokenRepository;

@Singleton("account-manager")
public class AccountManager implements IAccountManager {

    private AccountRepository accountRepository;
    private TokenRepository tokenRepository;

    @Autowired
    public AccountManager(AccountRepository accountRepository, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
    }


    @Override
    public @Nullable Account save(@NotNull Account account) {
        return accountRepository.save(account);
    }

    @Override
    public @Nullable Account addToken(@NotNull Account account, @NotNull Token token) {
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
}
