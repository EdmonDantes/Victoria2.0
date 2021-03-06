/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.money.impl;

import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thymeleaf.standard.expression.Each;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.money.IMoney;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Money implements IMoney<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "money_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(nullable = false, unique = true, updatable = false, length = 10)
    private String tag = "";

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true, optional = false, fetch = FetchType.EAGER)
    private LocalizationString name;

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public @NotNull String getTag() {
        return tag;
    }

    @Override
    public @Nullable ILocalizationString<Integer> getName() {
        return name;
    }
}
