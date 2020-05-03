package ru.liveproduction.victoria.core.entity.difficult.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;

@Singleton("ALW_TRUE")
@Qualifier("ALW_TRUE")
public class AlwaysTrueCompareDifficult implements ICompareDifficult {
    @Override
    public boolean checkStrings(String expected, String actual) {
        return true;
    }

    @Override
    public String getTag() {
        return "ALW_TRUE";
    }
}
