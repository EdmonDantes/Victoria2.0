/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account;

import ru.liveproduction.victoria.core.entity.account.impl.Account;

public interface IToken {

    Account getAccount();

    String getToken();

}
