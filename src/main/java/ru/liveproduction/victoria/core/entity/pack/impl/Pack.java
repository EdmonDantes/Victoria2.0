/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.pack.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.money.IPrice;
import ru.liveproduction.victoria.core.entity.money.impl.Price;
import ru.liveproduction.victoria.core.entity.pack.IPack;
import ru.liveproduction.victoria.core.entity.questions.IQuestion;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Pack implements IPack<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "pack_generator")
    private Integer id;

    @OneToOne(optional = false)
    private LocalizationString name;

    @OneToOne
    private LocalizationString description;

    @ManyToMany
    private List<Question> questions = new ArrayList<>();

    @OneToOne
    private Price price;

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public @NotNull ILocalizationString<Integer> getName() {
        return name;
    }

    @Override
    public @Nullable ILocalizationString<Integer> getDescription() {
        return description;
    }

    @Override
    public int getCountQuestions() {
        return questions.size();
    }

    @Override
    public @NotNull List<? extends IQuestion> getAllQuestions() {
        return questions;
    }

    @Override
    public @NotNull List<? extends IQuestion> getRandomQuestions(int count) {
        if (count < 1 || count > questions.size()) {
            return Collections.emptyList();
        }

        Set<Integer> result = new HashSet<>();
        SecureRandom random = new SecureRandom();

        while (result.size() < count) {
            result.add(random.nextInt(questions.size()));
        }

        return result.stream().map(index -> questions.get(index)).collect(Collectors.toList());
    }

    @Override
    public @Nullable IPrice<Integer> getPrice() {
        return price;
    }
}
