package ru.liveproduction.victoria.core.entity.category;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.IdOwner;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;

public interface ICategory<ID> extends IdOwner<ID> {

    @NotNull
    LocalizationString getName();

    @Nullable
    LocalizationString getDescription();

}
