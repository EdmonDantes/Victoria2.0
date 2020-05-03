package ru.liveproduction.victoria.core.entity.localization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface ILocalizationString<ID> {

    @Nullable
    ID getId();

    boolean isSupport(@NotNull IStoredLocale locale);

    @NotNull
    Set<? extends IStoredLocale> getSupportLocale();

    @Nullable
    String getLocaleString(@NotNull IStoredLocale locale);

}
