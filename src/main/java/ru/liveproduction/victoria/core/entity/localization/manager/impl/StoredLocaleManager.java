package ru.liveproduction.victoria.core.entity.localization.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.IStoredLocaleManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.StoredLocaleRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton("stored-locale-manager")
public class StoredLocaleManager implements IStoredLocaleManager {
    private StoredLocaleRepository storedLocaleRepository;
    private Map<String, Set<StoredLocale>> locales = new HashMap<>();

    @Autowired
    public StoredLocaleManager(StoredLocaleRepository storedLocaleRepository) {
        this.storedLocaleRepository = storedLocaleRepository;
    }

    @Override
    public void setUsingLocales(@NotNull String languageTag, @NotNull Set<StoredLocale> locales) {
        this.locales.put(languageTag, locales);
    }

    @Override
    public void addUsingLocales(@NotNull String languageTag, @Nullable StoredLocale locale) {
        if (locale != null) {
            this.locales.computeIfAbsent(languageTag, key -> new HashSet<>()).add(locale);
        }
    }

    @Override
    public void addUsingLocale(@Nullable StoredLocale locale) {
        if (locale == null) {
            return;
        }

        locales.keySet().stream().filter(langKey -> locale.getJavaLanguageTag().startsWith(langKey)).forEach(langKey -> addUsingLocales(langKey, locale));
        locales.computeIfAbsent(locale.getJavaLanguageTag(), key -> new HashSet<>()).add(locale);
    }

    @Override
    @NotNull
    public Set<StoredLocale> getUsingLocales(@NotNull String languageTag) {
        return this.locales.getOrDefault(languageTag, Collections.emptySet());
    }

    @Override
    @Nullable
    public StoredLocale getStoredLocaleFrom(@NotNull String languageTag) {
        return storedLocaleRepository.getByJavaLanguageTagIgnoreCase(languageTag).orElse(null);
    }

    @Override
    @Nullable
    public StoredLocale save(StoredLocale locale) {
        if (storedLocaleRepository.getByJavaLanguageTagIgnoreCase(locale.getJavaLanguageTag()).isEmpty()) {
            try {
                var saved = storedLocaleRepository.save(locale);

                addUsingLocale(saved);
                return saved;
            } catch (Exception e) {
                throw new RuntimeException("Can not save stored locale with language tag: " + locale.getJavaLanguageTag(), e);
            }
        }
        return null;
    }

    @Override
    public @NotNull Iterable<StoredLocale> getAllStoredLocales() {
        return storedLocaleRepository.findAll();
    }
}
