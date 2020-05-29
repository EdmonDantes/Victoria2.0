/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.impl.Token;

import javax.transaction.Transactional;

@Transactional
@Singleton("account-manager")
public interface IAccountManager {

    @Nullable
    Account save(@NotNull Account account);

    @Nullable
    Token login(@NotNull String login, @NotNull String password);

    @Nullable
    Account addToken(@NotNull Account account, @NotNull Token token);

    @Nullable
    Account getFromToken(@NotNull String token);

    @Nullable
    Account getFromLogin(@NotNull String login);

    @Nullable
    Account addPoints(@NotNull Account account, @NotNull Integer points);
}
