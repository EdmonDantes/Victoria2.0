/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.impl;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.liveproduction.victoria.core.entity.account.IAccount;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Account implements IAccount<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @GenericGenerator(name = "account_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(unique = true, length = 80, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY,mappedBy = "account")
    Set<Token> tokens = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        return login.equals(account.login);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + login.hashCode();
        return result;
    }
}
