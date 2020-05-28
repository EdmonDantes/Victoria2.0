/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

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

    /**
     * Check localization string support this locale.
     * @param locale
     * @return True, if localization string support this locale.
     */
    boolean isSupport(@NotNull IStoredLocale locale);

    /**
     * @return all supports locales
     */
    @NotNull
    Set<? extends IStoredLocale<?>> getSupportLocale();

    /**
     * @param locale
     * @return localization string for specific locale. Null if not support this locale.
     */
    @Nullable
    String getLocaleString(@NotNull IStoredLocale locale);

}
