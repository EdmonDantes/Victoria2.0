/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.impl;

import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of {@link ILocalizationString} which will saved to database
 */
@Entity
public class LocalizationString implements ILocalizationString<Integer> {

    public LocalizationString(){}

    public LocalizationString(Map<? extends StoredLocale, String> localizationStrings) {
        localizationString.putAll(localizationStrings);
    }

    public LocalizationString(Object... args) {
        for (int i = 0; i < args.length - 1; i+= 2) {
            if (args[i] instanceof StoredLocale && args[i + 1] instanceof String) {
                localizationString.put((StoredLocale) args[i], (String) args[i + 1]);
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localization_string_generator")
    @GenericGenerator(name = "localization_string_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @ElementCollection
    @CollectionTable
    @MapKeyColumn(updatable = false)
    private Map<StoredLocale, String> localizationString = new HashMap<>();

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isSupport(@NotNull IStoredLocale locale) {
        return localizationString.containsKey(locale);
    }

    @Override
    public @NotNull Set<? extends IStoredLocale<?>> getSupportLocale() {
        return localizationString.keySet();
    }

    @Override
    public @Nullable String getLocaleString(@NotNull IStoredLocale locale) {
        return localizationString.get(locale);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalizationString string = (LocalizationString) o;

        if (id != null ? !id.equals(string.id) : string.id != null) return false;
        return localizationString.equals(string.localizationString);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + localizationString.hashCode();
        return result;
    }
}
