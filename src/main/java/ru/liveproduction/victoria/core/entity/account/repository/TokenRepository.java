/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.impl.Token;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    List<Token> getAllByAccount_Id(Integer id);
    List<Token> getAllByAccount(Account account);

}
