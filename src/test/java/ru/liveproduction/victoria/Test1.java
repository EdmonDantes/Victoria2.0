/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedArray;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.LocalizationStringManager;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.StoredLocaleManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.StoredLocaleRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Autowired
    public LocalizationStringManager manager;

    @Autowired
    public StoredLocaleRepository repository;

    @Test
    public void testLocalizationString() {
        ILocalizationString<Integer> localizationString = manager.save("ru", "Привет", "en", "Hello");

        Optional<? extends StoredLocale> ruLocale = repository.getByJavaLanguageTagIgnoreCase("ru");
        Assert.assertTrue(ruLocale.isPresent());
        Assert.assertEquals("Привет", manager.getLocaleString(localizationString, ruLocale.get()));

        var enLocale = repository.getByJavaLanguageTagIgnoreCase("en");
        Assert.assertTrue(enLocale.isPresent());
        Assert.assertEquals("Hello", manager.getLocaleString(localizationString, enLocale.get()));
    }
}
