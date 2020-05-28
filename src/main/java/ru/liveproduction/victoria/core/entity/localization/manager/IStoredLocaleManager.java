package ru.liveproduction.victoria.core.entity.localization.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import java.util.Set;

@Singleton("stored-locale-manager")
public interface IStoredLocaleManager {

    void setUsingLocales(@NotNull String languageTag, @NotNull Set<StoredLocale> locales);
    void addUsingLocales(@NotNull String languageTag, @Nullable StoredLocale locale);
    void addUsingLocale(@Nullable StoredLocale locale);

    @NotNull
    Set<StoredLocale> getUsingLocales(@NotNull String languageTag);

    @Nullable
    StoredLocale getStoredLocaleFrom(@NotNull String languageTag);

    @Nullable
    StoredLocale save(@Nullable StoredLocale locale);

    @NotNull
    Iterable<StoredLocale> getAllStoredLocales();

}
