/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.localization.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;

/**
 * Class of manager {@link LocalizationString} repository.
 */
@Repository
public interface LocalizationStringRepository extends JpaRepository<LocalizationString, Integer> {}
