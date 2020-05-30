/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.account.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.account.IToken;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Token implements IToken {

    public Token() {}

    public Token(String token) {
        this.token = token;
    }

    @Id
    private String token;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Account account;


    @Nullable
    @Override
    @JsonIgnore
    public String getId() {
        return token;
    }
}
