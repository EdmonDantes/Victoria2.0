/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.pack.manager.impl;

import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.category.manager.ICategoryManager;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.LocalizationStringManager;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.pack.manager.IPackManager;
import ru.liveproduction.victoria.core.entity.pack.repository.PackRepository;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.entity.questions.manager.IQuestionManager;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Singleton("pack-manager")
public class PackManager implements IPackManager {

    private PackRepository packRepository;
    private ILocalizationStringManager localizationStringManager;
    private ICategoryManager categoryManager;
    private IQuestionManager questionManager;

    @Autowired
    public PackManager(PackRepository packRepository, ILocalizationStringManager localizationStringManager, ICategoryManager categoryManager, IQuestionManager questionManager) {
        this.packRepository = packRepository;
        this.localizationStringManager = localizationStringManager;
        this.categoryManager = categoryManager;
        this.questionManager = questionManager;
    }

    @Override
    public @Nullable Pack save(@NotNull Pack pack) {
        if (pack.getName().getId() == null) {
            pack.setName(localizationStringManager.save(pack.getName()));
        }

        if (pack.getDescription() != null && pack.getDescription().getId() == null) {
            pack.setDescription(localizationStringManager.save(pack.getDescription()));
        }

        for (Category category : pack.getCategories()) {
            if (category.getId() == null) {
                categoryManager.save(category);
            }
        }

        for (Question question : pack.getQuestions()) {
            if (question.getId() == null) {
                questionManager.save(question);
            }
        }

        return packRepository.save(pack);
    }

    @Override
    public boolean addQuestion(@NotNull Pack pack, @NotNull Question question) {
        if (pack.getCategories().contains(question.getCategory())) {
            pack.getQuestions().add(question);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public @NotNull Map<Category, List<Question>> getAllQuestions(@NotNull Pack pack) {

        if (!Hibernate.isInitialized(pack)) {
            Hibernate.initialize(pack);
        }

        Map<Category, List<Question>> result = new HashMap<>();
        for (Question question : pack.getAllQuestions()) {
            result.computeIfAbsent(question.getCategory(), key -> new ArrayList<>()).add(question);
        }

        return result;
    }

    @Override
    public @NotNull Map<Category, Set<Question>> getRandomQuestions(@NotNull Pack pack, int countCategories, int countQuestions) {

        if (countCategories < 1 || countQuestions < 1) {
            throw new IllegalArgumentException("Wrong category count or/and question count");
        }

        Map<Category, Set<Question>> result = new HashMap<>();

        Map<Category, List<Question>> allQuestions = getAllQuestions(pack);

        SecureRandom secureRandom = new SecureRandom();

        if (countCategories >= allQuestions.size()) {
            for (Category category : allQuestions.keySet()) {
                result.put(category, new HashSet<>(countQuestions));
            }
        } else {
            List<Category> categories = pack.getCategories();
            Category category = categories.get(secureRandom.nextInt(categories.size()));
            while (allQuestions.size() < countCategories) {
                result.computeIfAbsent(category, key -> new HashSet<>(countQuestions));
            }
        }

        for (Category category : result.keySet()){
            List<Question> questions = allQuestions.get(category);
            if (countQuestions >= questions.size()) {
                result.get(category).addAll(questions);
            } else {
                while (result.get(category).size() < countQuestions) {
                    result.get(category).add(questions.get(secureRandom.nextInt(questions.size())));
                }
            }
        }

        return result;
    }
}
