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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.repostiory.LocalizationStringRepository;
import ru.liveproduction.victoria.core.entity.localization.repostiory.StoredLocaleRepository;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import javax.transaction.Transactional;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Autowired
    @Qualifier("EQUALS")
    public ICompareDifficult difficult;

    @Autowired
    public Map<String,? extends ICompareDifficult> difficults;

    @Autowired
    public StoredLocaleRepository storedLocaleRepository;

    @Test
    public void testDifficult() {
        Assert.assertTrue(difficult.checkStrings("1", "1"));
        Assert.assertFalse(difficult.checkStrings("1", "2"));

        Question question = new Question();
        question.setId(12);

        try {
            question.checkAnswer(storedLocaleRepository.getOne(1), difficult, "12314");
            Assert.fail();
        } catch (VictoriaException ignore) {}

        for (Map.Entry<String, ? extends ICompareDifficult> stringEntry : difficults.entrySet()) {
            Assert.assertEquals(stringEntry.getKey(), stringEntry.getValue().getTag());
        }
    }

    @Autowired
    public LocalizationStringRepository localizationStringRepository;

    @Test
    @Transactional
    public void testLocalizationString() {
        var ruLocale = storedLocaleRepository.getByJavaLanguageTagIgnoreCase("RU");
        if (ruLocale.isEmpty()) {
            Assert.fail();
        }
        var engLocale = storedLocaleRepository.getByJavaLanguageTagIgnoreCase("en");
        if (engLocale.isEmpty()) {
            Assert.fail();
        }

        LocalizationString localizationString = new LocalizationString(ruLocale.get(), "Привет", engLocale.get(), "Hello");
        localizationString = localizationStringRepository.save(localizationString);
        Integer stringId = localizationString.getId();
        if (stringId == null) {
            Assert.fail();
        }

        System.out.println(stringId);
        System.out.println(localizationStringRepository.getOne(stringId).getLocaleString(ruLocale.get()));
        System.out.println(localizationStringRepository.getOne(stringId).getLocaleString(engLocale.get()));
    }
}
