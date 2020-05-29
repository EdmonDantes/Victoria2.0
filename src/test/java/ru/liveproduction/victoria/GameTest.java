/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.account.manager.IAccountManager;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.category.manager.ICategoryManager;
import ru.liveproduction.victoria.core.entity.game.impl.Game;
import ru.liveproduction.victoria.core.entity.game.manager.IGameManager;
import ru.liveproduction.victoria.core.entity.game.manager.IGameStateManager;
import ru.liveproduction.victoria.core.entity.localization.ILocalizationString;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.pack.manager.IPackManager;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTest {

    @Autowired
    public IGameManager gameManager;

    @Autowired
    public IGameStateManager gameStateManager;

    @Autowired
    public IAccountManager accountManager;

    @Autowired
    public ILocalizationStringManager localizationStringManager;

    @Autowired
    public IPackManager packManager;

    public Game game;

    public Account player1;
    public Account player2;

    public Pack pack;

    @Before
    public void setUp() throws Exception {
        pack = new Pack();
        Category category1 = new Category();
        category1.setName(localizationStringManager.save("en", "Category 1"));

        Category category2 = new Category();
        category2.setName(localizationStringManager.save("en", "Category 2"));
        pack.getCategories().add(category1);
        pack.getCategories().add(category2);

        Question question0 = new Question();
        question0.setCategory(category1);
        question0.setQuestion(localizationStringManager.save("en", "Question 0"));
        question0.setAnswer(localizationStringManager.save("en", "Answer"));
        question0.setPoints(20);

        pack.getQuestions().add(question0);

        Question question1 = new Question();
        question1.setCategory(category1);
        question1.setQuestion(localizationStringManager.save("en", "Question 1"));
        question1.setAnswer(localizationStringManager.save("en", "Answer"));
        question1.setPoints(50);

        pack.getQuestions().add(question1);

        Question question2 = new Question();
        question2.setCategory(category2);
        question2.setQuestion(localizationStringManager.save("en", "Question 2"));
        question2.setAnswer(localizationStringManager.save("en", "Answer"));
        question2.setPoints(30);

        pack.getQuestions().add(question2);

        Question question3 = new Question();
        question3.setCategory(category1);
        question3.setQuestion(localizationStringManager.save("en", "Question 3"));
        question3.setAnswer(localizationStringManager.save("en", "Answer"));
        question3.setPoints(40);

        pack.getQuestions().add(question3);

        pack.setName(localizationStringManager.save("en", "Test pack"));

        player1 = accountManager.save(new Account("player1", "pass"));
        player2 = accountManager.save(new Account("player2", "pass"));

        game = new Game("Game name", pack = packManager.save(pack), 2000, 10000, 2, 2, 2);
        game.setDifficultTag("EQUALS");
        game = gameManager.save(game);
        game = gameManager.joinToGame(game, player1);
        game = gameManager.joinToGame(game, player2);
    }

    @Test
    public void name() throws InterruptedException, ExecutionException, TimeoutException {
        Account account = gameStateManager.getCurrentPlayer(game).get(10, TimeUnit.SECONDS);
        System.out.println(account.getLogin());
    }
}
