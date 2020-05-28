/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.pack.impl;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.money.IPrice;
import ru.liveproduction.victoria.core.entity.money.impl.Price;
import ru.liveproduction.victoria.core.entity.pack.IPack;
import ru.liveproduction.victoria.core.entity.questions.IQuestion;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Data
public class Pack implements IPack<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "pack_generator")
    private Integer id;

    @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private LocalizationString name;

    @OneToOne(optional = true, orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private LocalizationString description;

    @OneToOne(optional = true, orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Price price;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Question> questions = new ArrayList<>();

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public @NotNull List<Question> getAllQuestions() {
        return questions;
    }

    @Override
    public @NotNull LocalizationString getName() {
        return name;
    }

    @Override
    public @Nullable LocalizationString getDescription() {
        return description;
    }

    @Override
    public @Nullable Price getPrice() {
        return price;
    }

    @Override
    public @NotNull List<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pack pack = (Pack) o;

        if (id != null ? !id.equals(pack.id) : pack.id != null) return false;
        if (!categories.equals(pack.categories)) return false;
        return questions.equals(pack.questions);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + categories.hashCode();
        result = 31 * result + questions.hashCode();
        return result;
    }
}
