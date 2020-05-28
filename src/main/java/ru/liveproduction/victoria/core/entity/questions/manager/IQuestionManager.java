package ru.liveproduction.victoria.core.entity.questions.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

@Singleton("question-manager")
public interface IQuestionManager {

    @Nullable
    Question save(@NotNull Question question);

    boolean checkAnswer(@NotNull Question question, @NotNull String difficultTag, @NotNull String answer, @NotNull String languageTag);

    @Nullable
    String getQuestionString(@NotNull Question question, @NotNull String languageTag);

    @Nullable
    String getAnswerString(@NotNull Question question, @NotNull String languageTag);

}
