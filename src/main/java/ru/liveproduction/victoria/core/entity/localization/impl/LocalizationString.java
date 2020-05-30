/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.json.LocalizationStringJsonDeserializer;
import ru.liveproduction.victoria.core.entity.localization.json.LocalizationStringJsonSerializer;
import ru.liveproduction.victoria.core.entity.localization.repostiory.LocalizationStringRepository;

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
@JsonDeserialize(using = LocalizationStringJsonDeserializer.class)
@JsonSerialize(using = LocalizationStringJsonSerializer.class)
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
    public @NotNull Map<StoredLocale, String> getLocalStrings() {
        return localizationString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalizationString string = (LocalizationString) o;

        return id != null ? id.equals(string.id) : string.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
