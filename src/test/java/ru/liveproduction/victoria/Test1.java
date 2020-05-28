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
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.category.manager.impl.CategoryManager;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.LocalizationStringManager;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.StoredLocaleManager;
import ru.liveproduction.victoria.core.entity.localization.repostiory.StoredLocaleRepository;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.pack.manager.IPackManager;
import ru.liveproduction.victoria.core.entity.pack.manager.impl.PackManager;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.entity.questions.manager.impl.QuestionManager;

import java.util.Collections;
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

    @Autowired
    public IPackManager packManager;

    @Autowired
    public CategoryManager categoryManager;

    @Autowired
    public QuestionManager questionManager;

    @Test
    public void testPackRepo() {
        Pack pack = new Pack();
        pack.setName(manager.save("en", "Test pack"));
        Category category = new Category();
        category.setName(manager.save("en", "Test category"));
        pack.getCategories().add(category);
        Question question = new Question();
        question.setCategory(category);
        question.setQuestion(new LocalizationString(Collections.singletonMap("en", "Test question")));
        question.setAnswer(manager.save("en", "Test answer"));
        question.setPoints(500);
        Assert.assertTrue(packManager.addQuestion(pack, question));

        packManager.save(pack);
    }
}
