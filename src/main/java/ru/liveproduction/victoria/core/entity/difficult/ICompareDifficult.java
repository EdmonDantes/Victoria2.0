/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.difficult;

import org.jetbrains.annotations.NotNull;
import ru.liveproduction.victoria.core.annotation.Singleton;

/**
 * Class for comparing users answer and right answer
 * @see ru.liveproduction.victoria.core.entity.questions.IQuestion
 */
@Singleton
public interface ICompareDifficult {

    /**
     * Compare two string and return true if answer was right
     * @param expected right answer
     * @param actual user's answer
     * @return true, if user's answer was right
     */
    boolean checkStrings(@NotNull String expected, @NotNull String actual);

    /**
     * @return Difficult's tag which equals component name in spring
     * @see org.springframework.stereotype.Component
     */
    @NotNull
    String getTag();

}
