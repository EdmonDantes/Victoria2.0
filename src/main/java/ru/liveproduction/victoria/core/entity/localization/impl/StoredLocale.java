/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.impl;

import lombok.Getter;
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
@Getter
public class StoredLocale implements IStoredLocale<Integer> {

    public StoredLocale() {}

    public StoredLocale(String lang) {
        this.lang = lang;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locale_generator")
    @GenericGenerator(name = "locale_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(nullable = false, unique = true, updatable = false, length = 34)
    private String lang = "";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoredLocale that = (StoredLocale) o;

        return lang.equals(that.lang);
    }

    @Override
    public int hashCode() {
        return lang.hashCode();
    }
}
