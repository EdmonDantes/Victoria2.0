package ru.liveproduction.victoria.core.entity.difficult.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;

@Singleton("ALW_FALSE")
@Qualifier("ALW_FALSE")
public class AlwaysFalseCompareDifficult implements ICompareDifficult {
    @Override
    public boolean checkStrings(String expected, String actual) {
        return false;
    }

    @Override
    public String getTag() {
        return "ALW_FALSE";
    }
}
