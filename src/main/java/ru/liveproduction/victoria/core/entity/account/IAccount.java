/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account;

import org.jetbrains.annotations.NotNull;
import ru.liveproduction.victoria.core.entity.IdOwner;
import ru.liveproduction.victoria.core.entity.account.impl.Token;

import java.util.Set;

public interface IAccount<ID> extends IdOwner<ID> {

    @NotNull
    String getLogin();

    @NotNull
    String getPassword();

    @NotNull
    Set<Token> getTokens();

    int getScope();

}
