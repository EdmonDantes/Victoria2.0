/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.pack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.IdOwner;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.money.impl.Price;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import javax.validation.constraints.NegativeOrZero;
import java.util.List;

public interface IPack<ID> extends IdOwner<ID> {

    @NotNull
    List<Question> getAllQuestions();

    @NotNull
    LocalizationString getName();

    @Nullable
    LocalizationString getDescription();

    @Nullable
    Price getPrice();

    @NotNull
    List<Category> getCategories();

}
