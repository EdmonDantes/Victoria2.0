/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.repostiory;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;

import java.util.Optional;

/**
 * Class for manage {@link StoredLocale} repository.
 */
@Repository
public interface StoredLocaleRepository extends JpaRepository<StoredLocale, Integer> {

    @NotNull
    Optional<? extends StoredLocale> getByJavaLanguageTagIgnoreCase(String javaLanguageTag);

}
