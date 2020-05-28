package ru.liveproduction.victoria.core.entity;

import org.jetbrains.annotations.Nullable;

public interface IdOwner<ID> {

    @Nullable
    ID getId();

}
