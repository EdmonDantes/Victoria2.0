package ru.liveproduction.victoria.core.entity.category.impl;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.category.ICategory;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Category implements ICategory<Integer> {

    public Category() {}

    public Category(@NotNull LocalizationString name, @Nullable LocalizationString description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @GenericGenerator(name = "category_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private LocalizationString name;

    @OneToOne(optional = true, orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private LocalizationString description;

    @Nullable
    @Override
    public Integer getId() {
        return id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }
}
