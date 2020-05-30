package ru.liveproduction.victoria.core.entity.localization.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.LocalizationStringRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public LocalizationString getById(@NotNull Integer localizationStringId) {
        return repository.findById(localizationStringId).orElse(null);
    }

    @Override
    @Nullable
    public String getLocaleString(@NotNull Integer localizationStringId, @NotNull StoredLocale storedLocale) {
        return getAllLocalizationString(localizationStringId).get(storedLocale);
    }

    @Override
    @Nullable
    public String getLocaleString(@NotNull Integer localizationStringId, @NotNull String languageTag) {
        StoredLocale storedLocale = storedLocaleManager.getStoredLocaleFrom(languageTag);
        return storedLocale == null ? null : getLocaleString(localizationStringId, storedLocale);
    }

    @Override
    @Transactional
    public @NotNull Map<StoredLocale, String> getAllLocalizationString(int id) {
        LocalizationString string = repository.findById(id).orElse(null);
        if (string == null) {
            return Collections.emptyMap();
        }

        return string.getLocalStrings();
    }

    @Override
    public @NotNull Set<StoredLocale> getSupportLocale(@NotNull Integer localizationStringId) {
        return getAllLocalizationString(localizationStringId).keySet();
    }
}
