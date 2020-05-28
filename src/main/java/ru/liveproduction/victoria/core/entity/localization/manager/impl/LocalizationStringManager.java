package ru.liveproduction.victoria.core.entity.localization.manager.impl;

import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.LocalizationStringRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Singleton("localization-string-manager")
public class LocalizationStringManager implements ILocalizationStringManager {

    private LocalizationStringRepository repository;
    private StoredLocaleManager storedLocaleManager;

    @Autowired
    public LocalizationStringManager(LocalizationStringRepository repository, StoredLocaleManager storedLocaleManager) {
        this.repository = repository;
        this.storedLocaleManager = storedLocaleManager;
    }

    @Override
    public @Nullable LocalizationString save(@NotNull LocalizationString localizationString) {
        return repository.save(localizationString);
    }

    @Override
    @Nullable
    public LocalizationString save(@NotNull Map<String, String> localizationString) {
        Map<StoredLocale, String> tmp = new HashMap<>();
        localizationString.forEach((langTag, string) -> {
            for (StoredLocale usingLocale : storedLocaleManager.getUsingLocales(langTag)) {
                tmp.put(usingLocale, string);
            }
        });
        if (tmp.size() > 0) {
            return save(new LocalizationString(tmp));
        } else {
            return null;
        }
    }

    @Override
    @Nullable
    public LocalizationString save(@NotNull String... args) {
        Map<String, String> tmp = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            tmp.put(args[i], args[i + 1]);
        }
        return save(tmp);
    }

    @Override
    @Nullable
    @Transactional
    public String getLocaleString(@NotNull ILocalizationString<?> string, @NotNull StoredLocale storedLocale) {
        if (!Hibernate.isInitialized(string)) {
            Hibernate.initialize(string);
        }

        return string.getLocaleString(storedLocale);
    }

    @Override
    @Nullable
    public String getLocaleString(@NotNull ILocalizationString<?> string, @NotNull String languageTag) {
        StoredLocale storedLocale = storedLocaleManager.getStoredLocaleFrom(languageTag);
        return storedLocale == null ? null : getLocaleString(string, storedLocale);
    }
}
