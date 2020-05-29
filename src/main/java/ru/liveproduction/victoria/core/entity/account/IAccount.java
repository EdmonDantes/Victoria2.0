/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account;

import ru.liveproduction.victoria.core.entity.IdOwner;

import java.util.List;

public interface IAccount<ID> extends IdOwner<ID> {

    String getLogin();
    String getPassword();

    List<String> getTokens();

}
