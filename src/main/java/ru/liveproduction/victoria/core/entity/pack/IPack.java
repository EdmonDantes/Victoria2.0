/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.pack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.money.IPrice;
import ru.liveproduction.victoria.core.entity.questions.IQuestion;

import java.util.List;

public interface IPack<ID> {

    @Nullable
    ID getId();

    @NotNull
    ILocalizationString<ID> getName();

    @Nullable
    ILocalizationString<ID> getDescription();

    int getCountQuestions();

    @NotNull
    List<? extends IQuestion> getAllQuestions();

    @NotNull
    List<? extends IQuestion> getRandomQuestions(int count);

    @Nullable
    IPrice<ID> getPrice();
}
