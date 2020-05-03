/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.money.impl;

import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.money.IMoney;
import ru.liveproduction.victoria.core.entity.money.IPrice;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Price implements IPrice<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "price_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    @MapKeyColumn
    private Map<Money, Double> prices = new HashMap<>();

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isSupportMoney(IMoney<Integer> money) {
        return prices.containsKey(money);
    }

    @Override
    public double getPriceForMoney(IMoney<Integer> money) {
        return prices.get(money);
    }
}
