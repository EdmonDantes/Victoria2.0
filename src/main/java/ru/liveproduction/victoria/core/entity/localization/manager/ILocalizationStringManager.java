/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import javax.transaction.Transactional;
import javax.validation.constraints.NegativeOrZero;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Manager for {@link ILocalizationString}. For lazy loading and other complex operations.
 */
@Transactional
@Singleton("localization-string-manager")
public interface ILocalizationStringManager {

    @Nullable
    LocalizationString save(@NotNull LocalizationString localizationString);

    @Nullable
    LocalizationString save(@NotNull Map<String, String> localizationString);

    @Nullable
    LocalizationString save(@NotNull String ...args);

    @Nullable
    LocalizationString getById(@NotNull Integer localizationStringId);

    @Nullable
    String getLocaleString(@NotNull Integer localizationStringId, @NotNull StoredLocale storedLocale);

    @Nullable
    String getLocaleString(@NotNull Integer localizationStringId, @NotNull String languageTag);

    @NotNull
    @Transactional
    Map<StoredLocale, String> getAllLocalizationString(int localizationStringId);

    @NotNull
    Set<StoredLocale> getSupportLocale(@NotNull Integer localizationStringId);
}
