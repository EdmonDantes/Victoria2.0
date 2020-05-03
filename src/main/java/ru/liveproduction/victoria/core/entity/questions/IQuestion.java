/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.questions;

import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.exception.VictoriaException;

@Component
public interface IQuestion {

    String getQuestionString(IStoredLocale locale);

    boolean checkAnswer(IStoredLocale locale, ICompareDifficult difficult, String answer) throws VictoriaException;

   Iterable<? extends IStoredLocale> getSupportLocales();

}
