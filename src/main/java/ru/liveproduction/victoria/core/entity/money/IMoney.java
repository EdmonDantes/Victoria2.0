/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.money;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;

/**
 * Interface for money type
 * @param <ID>
 */
public interface IMoney<ID> {

    @Nullable
    ID getId();

    @NotNull
    String getTag();

    @Nullable
    ILocalizationString<ID> getName();

}
