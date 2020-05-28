package ru.liveproduction.victoria.core.entity.difficult.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;

@Singleton("compare-difficult-manager")
public interface ICompareDifficultManager {

    @Nullable
    ICompareDifficult getFromTag(@NotNull String tag);

    @NotNull
    Iterable<String> getAllCompareDifficultTags();

}
