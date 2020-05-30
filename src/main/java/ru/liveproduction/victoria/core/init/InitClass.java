/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.IStoredLocaleManager;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.StoredLocaleManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.StoredLocaleRepository;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

@Singleton("init-class")
public class InitClass implements ApplicationListener<ContextRefreshedEvent> {

    private IStoredLocaleManager storedLocaleManager;

    @Autowired
    public InitClass(IStoredLocaleManager storedLocaleManager) {
        this.storedLocaleManager = storedLocaleManager;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Stream.of(Locale.getAvailableLocales()).map(Locale::toLanguageTag).sorted(String::compareTo).forEach(langTag -> {
            storedLocaleManager.save(new StoredLocale(langTag));
        });
    }
}
