/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.account.IAccount;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Account implements IAccount<String> {

    public Account() {}

    public Account(String login, String password) {
        this.login = login.toLowerCase();
        this.viewName = login;
        this.password = password;
    }

    @Id
    @JsonIgnore
    @Column(unique = true, length = 80, nullable = false)
    private String login;

    @Column(unique = true, length = 80, nullable = false)
    private String viewName;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "account")
    Set<Token> tokens = new HashSet<>();

    @Column(nullable = false)
    private int scope = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return login.equals(account.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Nullable
    @Override
    @JsonIgnore
    public String getId() {
        return login;
    }
}
