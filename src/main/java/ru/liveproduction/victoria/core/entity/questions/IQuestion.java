/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.questions;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.IdOwner;
import ru.liveproduction.victoria.core.entity.category.ICategory;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import java.util.List;

@Component
public interface IQuestion<ID> extends IdOwner<ID> {

    @NotNull
    LocalizationString getQuestion();

    @NotNull
    LocalizationString getAnswer();

    @NotNull
    Category getCategory();

    int getPoints();
}
