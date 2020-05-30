/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import java.util.Map;
import java.util.Set;

/**
 * Class for localization string value.
 * @param <ID> which id's type must use in database
 */
@Component
public interface ILocalizationString<ID> {

    /**
     * @return localization string's id. Null if localization string didn't saved database
     */
    @Nullable
    ID getId();

    @NotNull
    Map<StoredLocale, String> getLocalStrings();
}
