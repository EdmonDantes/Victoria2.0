package ru.liveproduction.victoria.core.entity.localization;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

public interface IStoredLocale<ID> {

    @Nullable
    ID getId();

    @NotNull
    String getJavaLanguageTag();
}
