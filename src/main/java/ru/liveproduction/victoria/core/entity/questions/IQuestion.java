package ru.liveproduction.victoria.core.entity.questions;

import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import java.util.Locale;

@Component
public interface IQuestion {

    String getQuestionString(IStoredLocale locale);

    boolean checkAnswer(IStoredLocale locale, ICompareDifficult difficult, String answer) throws VictoriaException;

   Iterable<? extends IStoredLocale> getSupportLocales();

}
