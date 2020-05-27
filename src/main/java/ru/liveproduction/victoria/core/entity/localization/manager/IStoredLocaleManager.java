package ru.liveproduction.victoria.core.entity.localization.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import java.util.Set;

public interface IStoredLocaleManager {

    void setUsingLocales(@NotNull String languageTag, @NotNull Set<StoredLocale> locales);
    void addUsingLocales(@NotNull String languageTag, @Nullable StoredLocale locale);
    void addUsingLocale(@Nullable StoredLocale locale);
    @NotNull
    Set<StoredLocale> getUsingLocales(@NotNull String languageTag);

    StoredLocale save(StoredLocale locale);

}
