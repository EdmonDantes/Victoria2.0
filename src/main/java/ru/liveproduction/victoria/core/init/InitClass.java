package ru.liveproduction.victoria.core.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.repostiory.IStoredLocaleRepository;

import java.util.Locale;

@Singleton("init-class")
public class InitClass implements ApplicationListener<ContextRefreshedEvent> {

    private IStoredLocaleRepository storedLocaleRepository;

    @Autowired
    public void setStoredLocaleRepository(IStoredLocaleRepository storedLocaleRepository) {
        this.storedLocaleRepository = storedLocaleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (storedLocaleRepository.getByJavaLanguageTagIgnoreCase(locale.toLanguageTag()).isEmpty()) {
                storedLocaleRepository.save(new StoredLocale(locale.toLanguageTag()));
            }
        }
    }
}
