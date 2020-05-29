/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.game.impl.Game;
import ru.liveproduction.victoria.core.entity.game.manager.IGameManager;
import ru.liveproduction.victoria.core.entity.game.manager.IGameStateManager;
import ru.liveproduction.victoria.core.entity.game.repository.GameRepository;
import ru.liveproduction.victoria.core.entity.pack.manager.IPackManager;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@Singleton("game-manager")
public class GameManager implements IGameManager {

    private GameRepository gameRepository;
    private IGameStateManager gameStateManager;
    private IPackManager packManager;

    private final Map<String, Integer> playingInGame = new ConcurrentHashMap<>();

    @Autowired
    public GameManager(GameRepository gameRepository, IGameStateManager gameStateManager, IPackManager packManager) {
        this.gameRepository = gameRepository;
        this.gameStateManager = gameStateManager;
        this.packManager = packManager;
    }

    @Override
    public @Nullable Game save(@NotNull Game game) {
        Map<Category, Set<Question>> questions;
        if ((questions = game.getQuestionsMap()) == null) {
            questions = packManager.getRandomQuestions(game.getPack(), game.getCountQuestions(), game.getCountCategories());
        }

        Game result = gameRepository.save(game);
        result.setQuestionsMap(questions);
        return result;

    }

    @Override
    public @Nullable Game joinToGame(@NotNull Game game, @NotNull Account account) {
        synchronized (game) {
            if (game.getStartTime() != null) {
                return null;
            }

            if (playingInGame.containsKey(account.getId())) {
                return gameRepository.findById(playingInGame.get(account.getId())).orElse(null);
            }

            game.getPlayers().add(account);
            playingInGame.put(account.getId(), game.getId());
            Game result = save(game);
            if (game.getPlayers().size() >= result.getMaxPlayers()) {
                startGame(result);
            }
            return result;
        }
    }

    @Override
    public @Nullable Game leaveFromGame(@NotNull Game game, @NotNull Account account) {
        Integer gameId = playingInGame.get(account.getId());
        if (game.getId().equals(gameId)) {
            game.getPlayers().remove(account);
            return save(game);
        } else {
            return null;
        }
    }

    @Override
    public @Nullable Game startGame(@NotNull Game game) {
        gameStateManager.startGame(game);
        return game;
    }


}
