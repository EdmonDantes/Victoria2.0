/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.pack.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Singleton("pack-manager")
public interface IPackManager {

    @Nullable
    Pack save(@NotNull Pack pack);

    boolean addQuestion(@NotNull Pack pack, @NotNull Question question);

    @NotNull
    @Transactional
    Map<Category, List<Question>> getAllQuestions(@NotNull Pack pack);

    @NotNull
    Map<Category, Set<Question>> getRandomQuestions(@NotNull Pack pack, int countCategories, int countQuestions);

}
