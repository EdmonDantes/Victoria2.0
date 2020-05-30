/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.game.IGame;
import ru.liveproduction.victoria.core.entity.game.impl.Game;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import java.util.concurrent.CompletableFuture;

public interface IGameStateManager {

    void startGame(@NotNull Game game);

    void endGame(@NotNull Game game);

    CompletableFuture<Account> getCurrentPlayer(@NotNull Game game);

    CompletableFuture<Question> getCurrentQuestion(@NotNull Game game);

    CompletableFuture<Long> getCurrentTimeForRead(@NotNull Game game);

    CompletableFuture<Long> getCurrentTimeForWrite(@NotNull Game game);

    CompletableFuture<Boolean> checkAnswer(@NotNull Game game, @NotNull String answer, @NotNull String languageTag);

    CompletableFuture<Boolean> chooseQuestion(@NotNull Game game, @NotNull Question question);

}
