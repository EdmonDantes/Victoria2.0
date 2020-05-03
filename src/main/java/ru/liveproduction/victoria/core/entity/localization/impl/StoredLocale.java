/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.impl;

import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Implementation of {@link IStoredLocale} which will saved to database
 */
@Entity
public class StoredLocale implements IStoredLocale<Integer> {

    public StoredLocale() {}

    public StoredLocale(String javaLanguageTag) {
        this.javaLanguageTag = javaLanguageTag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "locale_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String javaLanguageTag = "";

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    @NotNull
    public String getJavaLanguageTag() {
        return javaLanguageTag;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IStoredLocale) {
            return id.equals(((IStoredLocale) obj).getId()) || javaLanguageTag.equals(((IStoredLocale) obj).getJavaLanguageTag());
        }
        return false;
    }
}
