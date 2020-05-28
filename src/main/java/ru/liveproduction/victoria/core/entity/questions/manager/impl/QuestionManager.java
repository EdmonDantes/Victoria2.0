/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.questions.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.category.manager.ICategoryManager;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.difficult.manager.ICompareDifficultManager;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.entity.questions.manager.IQuestionManager;
import ru.liveproduction.victoria.core.entity.questions.repository.QuestionRepository;

@Singleton("question-manager")
public class QuestionManager implements IQuestionManager {

    private ILocalizationStringManager localizationStringManager;
    private ICompareDifficultManager compareDifficultManager;
    private ICategoryManager categoryManager;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionManager(ILocalizationStringManager localizationStringManager, ICompareDifficultManager compareDifficultManager, ICategoryManager categoryManager, QuestionRepository questionRepository) {
        this.localizationStringManager = localizationStringManager;
        this.compareDifficultManager = compareDifficultManager;
        this.categoryManager = categoryManager;
        this.questionRepository = questionRepository;
    }

    @Override
    public @Nullable Question save(@NotNull Question question) {
        if (question.getQuestion().getId() == null) {
            question.setQuestion(localizationStringManager.save(question.getQuestion()));
        }

        if (question.getAnswer().getId() == null) {
            question.setAnswer(localizationStringManager.save(question.getAnswer()));
        }

        if (question.getCategory().getId() == null) {
            question.setCategory(categoryManager.save(question.getCategory()));
        }

        return questionRepository.save(question);
    }

    @Override
    public boolean checkAnswer(@NotNull Question question, @NotNull String difficultTag, @NotNull String answer, @NotNull String languageTag) {
        ICompareDifficult compareDifficult = compareDifficultManager.getFromTag(difficultTag);
        if (compareDifficult == null) {
            throw new IllegalArgumentException("Wrong difficult tag");
        }

        String answerString = getAnswerString(question, languageTag);
        if (answerString == null) {
            throw new IllegalArgumentException("Wrong language tag");
        }

        return compareDifficult.checkStrings(answerString, answer);
    }

    @Override
    public @Nullable String getQuestionString(@NotNull Question question, @NotNull String languageTag) {
        return localizationStringManager.getLocaleString(question.getQuestion(), languageTag);
    }

    @Override
    public @Nullable String getAnswerString(@NotNull Question question, @NotNull String languageTag) {
        return localizationStringManager.getLocaleString(question.getAnswer(), languageTag);
    }
}
