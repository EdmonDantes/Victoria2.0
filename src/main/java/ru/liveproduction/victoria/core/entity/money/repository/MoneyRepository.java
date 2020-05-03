/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.money.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.money.impl.Money;

import java.util.Optional;

/**
 * Class for manage {@link Money} repository.
 */
@Repository
public interface MoneyRepository extends JpaRepository<Money, Integer> {

    Optional<? extends Money> getByTagIgnoreCase(String tag);

}
