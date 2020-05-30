package ru.liveproduction.victoria.core.entity.category.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.category.impl.Category;

import java.util.List;

@Singleton("category-manager")
public interface ICategoryManager {

    @Nullable
    Category save(@NotNull Category category);

    @Nullable
    Category getById(int id);

    @Nullable
    String getName(@NotNull Category category, @NotNull String languageTag);

    @Nullable
    String getDescription(@NotNull Category category, @NotNull String languageTag);

    @NotNull
    List<Category> getAllCategories();
}
