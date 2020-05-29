/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.game.impl.Game;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

@Singleton("game-manager")
public interface IGameManager {

    @Nullable
    Game save(@NotNull Game game);

    @Nullable
    Game leaveFromGame(@NotNull Game game, @NotNull Account account);

    @Nullable
    Game joinToGame(@NotNull Game game, @NotNull Account account);

    @Nullable
    Game startGame(@NotNull Game game);

}
