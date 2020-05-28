/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.entity.questions.impl;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.difficult.ICompareDifficult;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.questions.IQuestion;
import ru.liveproduction.victoria.core.exception.VictoriaException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Question implements IQuestion<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "question_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @OneToOne(optional = false, orphanRemoval = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private LocalizationString question;

    @OneToOne(optional = false, orphanRemoval = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private LocalizationString answer;

    private int points = 0;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Category category;

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public @NotNull Iterable<? extends IStoredLocale<?>> getSupportLocales() {
        Set<? extends IStoredLocale<?>> result = new HashSet<>(question.getSupportLocale());
        result.retainAll(answer.getSupportLocale());
        return result;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (id != null ? !id.equals(question1.id) : question1.id != null) return false;
        return question.equals(question1.question);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + question.hashCode();
        return result;
    }
}
