package ru.liveproduction.victoria.core.entity.difficult.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;

@Singleton("EQUALS")
@Qualifier("EQUALS")
public class EqualsCompareDifficult implements ICompareDifficult {

    @Override
    public boolean checkStrings(String expected, String actual) {
        return expected.equals(actual);
    }

    @Override
    public String getTag() {
        return "EQUALS";
    }
}
