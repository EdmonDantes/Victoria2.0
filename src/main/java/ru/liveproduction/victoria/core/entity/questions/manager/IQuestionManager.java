package ru.liveproduction.victoria.core.entity.questions.manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Singleton("question-manager")
public interface IQuestionManager {

    @Nullable
    Question save(@NotNull Question question);

    @Nullable
    Question save(int points, int categoryId, Map<String, List<String>> answerAndQuestion);

    @Nullable
    Question getById(Integer id);

    boolean checkAnswer(@NotNull Question question, @NotNull String difficultTag, @NotNull String answer, @NotNull String languageTag);

    @Nullable
    String getQuestionString(@NotNull Question question, @NotNull String languageTag);

    @Nullable
    String getAnswerString(@NotNull Question question, @NotNull String languageTag);

    @NotNull
    public Set<StoredLocale> getSupportLocale(@NotNull Question question);

}
