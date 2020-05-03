package ru.liveproduction.victoria.core.entity.localization.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
public class LocalizationString implements ILocalizationString<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @ElementCollection
    @CollectionTable
    @MapKeyColumn
    private Map<StoredLocale, String> localizationString;

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
    public @NotNull Set<? extends IStoredLocale> getSupportLocale() {
        return localizationString.keySet();
    }

    @Override
    public @Nullable String getLocaleString(@NotNull IStoredLocale locale) {
        return localizationString.get(locale);
    }
}
