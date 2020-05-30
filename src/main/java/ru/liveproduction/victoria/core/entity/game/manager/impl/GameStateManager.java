/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.manager.impl;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.liveproduction.victoria.core.annotation.Singleton;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.manager.IAccountManager;
import ru.liveproduction.victoria.core.entity.game.impl.Game;
import ru.liveproduction.victoria.core.entity.game.manager.IGameManager;
import ru.liveproduction.victoria.core.entity.game.manager.IGameStateManager;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.entity.questions.manager.IQuestionManager;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Singleton("game-state-manager")
public class GameStateManager implements IGameStateManager {

    private IQuestionManager questionManager;
    private IAccountManager accountManager;
    private IGameManager gameManager;

    private Map<Integer, Thread> gameThreads = new ConcurrentHashMap<>();
    private Map<Integer, Queue<Action>> actions = new ConcurrentHashMap<>();
    private Map<Integer, Map<String, Integer>> gamePoints = new ConcurrentHashMap<>();

    @Autowired
    public void setQuestionManager(IQuestionManager questionManager) {
        this.questionManager = questionManager;
    }

    @Autowired
    public void setAccountManager(IAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Autowired
    public void setGameManager(IGameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void startGame(@NotNull Game game) {

        if (gameThreads.containsKey(game.getId())) {
            return;
        }

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Integer> points = new ConcurrentHashMap<>();

                for (Account player : game.getPlayers()) {
                    points.put(player.getId(), 0);
                }

                Queue<Action> actionsQueue = new LinkedBlockingQueue<>();
                actions.put(game.getId(), actionsQueue);

                int maxIndexPlayer = game.getPlayers().size() - 1;
                int currentPlayerIndex = new SecureRandom().nextInt(maxIndexPlayer + 1);


                Question currentQuestion = null;
                Long startTime = null;

                while (game.getQuestionsMap().size() > 0 && game.getPlayers().size() > 1) {

                    if (startTime != null && System.currentTimeMillis() - startTime > game.getTimeForRead() + game.getTimeForWrite()) {
                        startTime = null;
                        Set<Question> questions = game.getQuestionsMap().get(currentQuestion.getCategory());
                        questions.remove(currentQuestion);
                        if (questions.isEmpty()) {
                            game.getQuestionsMap().remove(currentQuestion.getCategory());
                        }
                        currentQuestion = null;
                        if (currentPlayerIndex > game.getPlayers().size()) {
                            currentPlayerIndex = 0;
                        }
                    }

                    if (actionsQueue.isEmpty()) {
                        Thread.yield();
                        continue;
                    }

                    Action action = actionsQueue.poll();
                    if (action.getFuture().isCancelled()) {
                        continue;
                    }

                    switch (action.getId()) {
                        case 1:
                            action.getFuture().complete(game.getPlayers().get(currentPlayerIndex));
                        break;
                        case 2:
                            if (currentQuestion != null) {
                                action.getFuture().complete(currentQuestion);
                            } else {
                                actionsQueue.add(action);
                            }
                            break;
                        case 3:
                            if (startTime != null) {
                                long timeEstimate = System.currentTimeMillis() - startTime;
                                if (timeEstimate > game.getTimeForRead()) {
                                    action.getFuture().complete(0L);
                                } else {
                                    action.getFuture().complete(game.getTimeForRead() - timeEstimate);
                                }
                            } else {
                                action.getFuture().complete(null);
                            }
                            break;
                        case 4:
                            if (startTime != null) {
                                long time = System.currentTimeMillis() - startTime - game.getTimeForRead();
                                if (time > 0) {
                                    action.getFuture().complete(Math.max(game.getTimeForWrite() - time, 0L));
                                } else {
                                    action.getFuture().complete(0L);
                                }
                            } else {
                                action.getFuture().complete(null);
                            }
                            break;
                        case 5:
                            if (startTime != null) {
                                long time = System.currentTimeMillis() - startTime - game.getTimeForRead();
                                if (time > 0) {
                                    String languageTag = String.valueOf(action.getParams().get("LanguageTag"));
                                    String answer = String.valueOf(action.getParams().get("Answer"));
                                    String accountId = String.valueOf(action.getParams().get("Account"));

                                    if (StringUtils.isEmpty(languageTag) || StringUtils.isEmpty(answer) || !points.containsKey(accountId)) {
                                        action.getFuture().complete(null);
                                    }

                                    boolean result = questionManager.checkAnswer(currentQuestion, game.getDifficultTag(), answer, languageTag);
                                    action.getFuture().complete(result);
                                    points.put(accountId, points.get(accountId) + (result ? currentQuestion.getPoints() : -currentQuestion.getPoints()));
                                    if (result) {
                                        startTime = null;
                                        Set<Question> questions = game.getQuestionsMap().get(currentQuestion.getCategory());
                                        questions.remove(currentQuestion);
                                        if (questions.isEmpty()) {
                                            game.getQuestionsMap().remove(currentQuestion.getCategory());
                                        }
                                        currentQuestion = null;
                                        currentPlayerIndex++;
                                        if (currentPlayerIndex > game.getPlayers().size()) {
                                            currentPlayerIndex = 0;
                                        }
                                    }
                                    continue;
                                }
                            }
                            action.getFuture().complete(null);
                            break;
                        case 6:
                            if (currentQuestion != null) {
                                action.getFuture().complete(null);
                                continue;
                            }

                            String accountId = String.valueOf(action.getParams().get("Account"));
                            if (game.getPlayers().get(currentPlayerIndex).getId().equals(accountId)) {
                                action.getFuture().complete(null);
                                continue;
                            }

                            Question question = (Question) action.getParams().get("Question");
                            for (Question gameQuestion : game.getQuestions()) {
                                if (gameQuestion.getId().equals(question.getId())) {
                                    currentQuestion = gameQuestion;
                                    startTime = System.currentTimeMillis();
                                }
                            }

                            break;
                    }
                }

                endGame(game);

            }
        });
        th.start();
        gameThreads.put(game.getId(), th);
    }

    @Override
    public void endGame(@NotNull Game game) {
        gameThreads.remove(game.getId());
        actions.remove(game.getId());
        for (Map.Entry<String, Integer> stringIntegerEntry : gamePoints.remove(game.getId()).entrySet()) {
            accountManager.addPoints(accountManager.getFromLogin(stringIntegerEntry.getKey()), stringIntegerEntry.getValue());
        }
        game.setEndTime(System.currentTimeMillis());
        gameManager.save(game);
    }

    private CompletableFuture createAction(Game game, int id) {
        Queue<Action> queue = this.actions.get(game.getId());
        if (queue != null) {
            Action action = new Action(id);
            queue.add(action);
            return action.getFuture();
        }
        return CompletableFuture.failedFuture(new RuntimeException("Wrong game"));
    }

    @Override
    public CompletableFuture<Account> getCurrentPlayer(@NotNull Game game) {
        return createAction(game, 1);
    }

    @Override
    public CompletableFuture<Question> getCurrentQuestion(@NotNull Game game) {
        return createAction(game, 2);
    }

    @Override
    public CompletableFuture<Long> getCurrentTimeForRead(@NotNull Game game) {
        return createAction(game, 3);
    }

    @Override
    public CompletableFuture<Long> getCurrentTimeForWrite(@NotNull Game game) {
        return createAction(game, 4);
    }

    @Override
    public CompletableFuture<Boolean> checkAnswer(@NotNull Game game, @NotNull String answer, @NotNull String languageTag) {
        return createAction(game, 5);
    }

    @Override
    public CompletableFuture<Boolean> chooseQuestion(@NotNull Game game, @NotNull Question question) {
        return createAction(game, 6);
    }
}
