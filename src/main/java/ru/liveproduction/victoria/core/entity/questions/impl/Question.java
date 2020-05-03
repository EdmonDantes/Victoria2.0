/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.questions.impl;

import org.hibernate.annotations.GenericGenerator;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.questions.IQuestion;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Question implements IQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "question_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @OneToOne(optional = false)
    private LocalizationString questionString;

    @OneToOne(optional = false)
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
        if (answerString == null) {
            throw new VictoriaException("Can not get answer localization string for languageTag %s for question with id %d. Haven't answer", languageTag, id);
        }
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
