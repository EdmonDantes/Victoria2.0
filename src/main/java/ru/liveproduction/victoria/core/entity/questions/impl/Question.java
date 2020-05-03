package ru.liveproduction.victoria.core.entity.questions.impl;

import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.questions.IQuestion;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Question implements IQuestion {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private LocalizationString questionString;

    @OneToOne
    private LocalizationString answerString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getQuestionString(IStoredLocale locale) {
        return questionString.getLocaleString(locale);
    }

    @Override
    public boolean checkAnswer(IStoredLocale languageTag, ICompareDifficult difficult, String answer) throws VictoriaException{
        String localizationString = answerString.getLocaleString(languageTag);
        if (localizationString == null) {
            throw new VictoriaException("Can not get answer localization string for languageTag %s for question with id %d", languageTag, id);
        }
        return difficult.checkStrings(localizationString, answer);
    }

    @Override
    public Iterable<? extends IStoredLocale> getSupportLocales() {
        Set<? extends IStoredLocale> questionSupportLocale = questionString.getSupportLocale();
        return answerString.getSupportLocale()
                .stream()
                .map(locale -> questionSupportLocale.contains(locale) ? locale : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
