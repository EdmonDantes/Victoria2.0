package ru.liveproduction.victoria.core.entity.difficult.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.difficult.manager.ICompareDifficultManager;

import java.util.Map;

@Singleton("compare-difficult-manager")
public class CompareDifficultManager implements ICompareDifficultManager {

    private Map<String, ? extends ICompareDifficult> implementation;

    @Autowired
    public CompareDifficultManager(Map<String, ? extends ICompareDifficult> implementation) {
        this.implementation = implementation;
    }

    @Override
    public @Nullable ICompareDifficult getFromTag(@NotNull String tag) {
        return implementation.get(tag);
    }

    @Override
    public @NotNull Iterable<String> getAllCompareDifficultTags() {
        return implementation.keySet();
    }
}
