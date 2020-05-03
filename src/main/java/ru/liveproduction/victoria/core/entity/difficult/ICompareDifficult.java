package ru.liveproduction.victoria.core.entity.difficult;

import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.annotation.Singleton;

@Singleton
public interface ICompareDifficult {

    boolean checkStrings(String expected, String actual);
    String getTag();

}
