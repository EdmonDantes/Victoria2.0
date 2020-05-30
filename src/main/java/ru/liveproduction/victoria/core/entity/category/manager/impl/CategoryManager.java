package ru.liveproduction.victoria.core.entity.category.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.category.manager.ICategoryManager;
import ru.liveproduction.victoria.core.entity.category.repository.CategoryRepository;
import ru.liveproduction.victoria.core.entity.localization.manager.impl.LocalizationStringManager;

import java.util.List;

@Singleton("category-manager")
public class CategoryManager implements ICategoryManager {

    private CategoryRepository categoryRepository;
    private LocalizationStringManager localizationStringManager;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, LocalizationStringManager localizationStringManager) {
        this.categoryRepository = categoryRepository;
        this.localizationStringManager = localizationStringManager;
    }

    @Override
    @Nullable
    public Category save(@NotNull Category category) {
        if (category.getName().getId() == null) {
            category.setName(localizationStringManager.save(category.getName()));
        }

        if (category.getDescription() != null && category.getDescription().getId() == null) {
            category.setDescription(localizationStringManager.save(category.getDescription()));
        }

        return categoryRepository.save(category);
    }

    @Override
    public @Nullable Category getById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Nullable
    public String getName(@NotNull Category category, @NotNull String languageTag) {
        if (category.getName() != null && category.getName().getId() != null) {
            return localizationStringManager.getLocaleString(category.getName().getId(), languageTag);
        }
        return null;
    }

    @Override
    @Nullable
    public String getDescription(@NotNull Category category, @NotNull String languageTag) {
        if (category.getDescription() != null && category.getDescription().getId() != null) {
            return localizationStringManager.getLocaleString(category.getDescription().getId(), languageTag);
        }
        return null;
    }

    @Override
    public @NotNull List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
