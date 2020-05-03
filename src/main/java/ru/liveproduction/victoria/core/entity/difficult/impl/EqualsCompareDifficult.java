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
 * Implementation of {@link ICompareDifficult} which always return true in checkStrings, if string is equals
 */
@Singleton("EQUALS")
@Qualifier("EQUALS")
public class EqualsCompareDifficult implements ICompareDifficult {

    @Override
    public boolean checkStrings(@NotNull String expected, @NotNull String actual) {
        return expected.equals(actual);
    }

    @Override
    @NotNull
    public String getTag() {
        return "EQUALS";
    }
}
