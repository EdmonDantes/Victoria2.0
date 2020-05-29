/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.IdOwner;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGame<ID> extends IdOwner<ID> {

    @NotNull
    String getName();

    @Nullable
    Long getStartTime();

    @Nullable
    Long getEndTime();

    @NotNull
    List<Account> getPlayers();

    int getMaxPlayers();

    @NotNull
    Set<Question> getQuestions();

    @Nullable
    Map<Category, Set<Question>> getQuestionsMap();

    @NotNull
    Pack getPack();

    long getTimeForRead();

    long getTimeForWrite();

    int getCountCategories();
    int getCountQuestions();

    String getDifficultTag();
}
