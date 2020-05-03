package ru.liveproduction.victoria.core;

import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.impl.EqualsCompareDifficult;

@Singleton
public class TestManager {

    private EqualsCompareDifficult difficult;

    @Autowired
    public void setDifficult(EqualsCompareDifficult difficult) {
        this.difficult = difficult;
        System.out.println("Create TestManager");
    }
}
