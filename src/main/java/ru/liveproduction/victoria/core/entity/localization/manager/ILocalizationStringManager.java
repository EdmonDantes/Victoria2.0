/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.manager;

import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Manager for {@link ILocalizationString}. For lazy loading and other complex operations.
 */
@Component
@Transactional
public interface ILocalizationStringManager<ID> {

    ILocalizationString<ID> save(Map<? extends StoredLocale, String> localizationString);
    ILocalizationString<ID> save(Object ...args);

    String getLocaleString(ILocalizationString<ID> string, StoredLocale storedLocale);

}
