/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

/**
 * Interface for stored localization information
 * @param <ID> which id's type must use in database
 */
public interface IStoredLocale<ID> {

    /**
     * @return stored locale's id. Null if locale didn't saved to database.
     */
    @Nullable
    ID getId();

    /**
     * @return Language tag in Java programming language.
     */
    @NotNull
    String getLang();
}
