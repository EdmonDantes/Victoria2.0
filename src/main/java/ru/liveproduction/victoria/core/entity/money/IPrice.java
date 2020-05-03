/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.money;

import org.jetbrains.annotations.Nullable;

public interface IPrice<ID> {

    @Nullable
    ID getId();

    boolean isSupportMoney(IMoney<ID> money);

    double getPriceForMoney(IMoney<ID> money);

}
