/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.difficult.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;

/**
 * Implementation of {@link ICompareDifficult} which always return true in checkStrings
 */
@Singleton("ALW_TRUE")
@Qualifier("ALW_TRUE")
public class AlwaysTrueCompareDifficult implements ICompareDifficult {
    @Override
    public boolean checkStrings(@NotNull String expected, @NotNull String actual) {
        return true;
    }

    @Override
    @NotNull
    public String getTag() {
        return "ALW_TRUE";
    }
}
