package ru.liveproduction.victoria.core.entity.pack;

import ru.liveproduction.victoria.core.entity.questions.IQuestion;

import java.util.List;
import java.util.Locale;

public interface IPack {

    String getName(Locale locale);
    String getName(String languageTag);

    int getCountQuestions();

    List<? extends IQuestion> getAllQuestions();
    List<? extends IQuestion> getRandomQuestions(int count);

    List<Locale> getLocales();
    List<String> getLanguageTags();

    int getQuestionDifficult();
}
