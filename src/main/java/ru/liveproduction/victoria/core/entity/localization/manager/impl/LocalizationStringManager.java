package ru.liveproduction.victoria.core.entity.localization.manager.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.LocalizationStringRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class LocalizationStringManager implements ILocalizationStringManager<Integer> {

    private LocalizationStringRepository repository;
    private StoredLocaleManager storedLocaleManager;

    @Autowired
    public LocalizationStringManager(LocalizationStringRepository repository, StoredLocaleManager storedLocaleManager) {
        this.repository = repository;
        this.storedLocaleManager = storedLocaleManager;
    }

    @Override
    public ILocalizationString<Integer> save(Map<String, String> localizationString) {
        Map<StoredLocale, String> tmp = new HashMap<>();
        localizationString.forEach((langTag, string) -> {
            for (StoredLocale usingLocale : storedLocaleManager.getUsingLocales(langTag)) {
                tmp.put(usingLocale, string);
            }
        });
        return repository.save(new LocalizationString(tmp));
    }

    @Override
    public ILocalizationString<Integer> save(String... args) {
        Map<String, String> tmp = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            tmp.put(args[i], args[i + 1]);
        }
        return save(tmp);
    }

    @Override
    @Transactional
    public String getLocaleString(ILocalizationString<Integer> string, StoredLocale storedLocale) {
        if (!Hibernate.isInitialized(string)) {
            Hibernate.initialize(string);
        }

        return string.getLocaleString(storedLocale);
    }
}
