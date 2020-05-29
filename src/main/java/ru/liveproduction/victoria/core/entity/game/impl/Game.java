/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.impl;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.game.IGame;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Data
public class Game implements IGame {

    public Game() {}

    public Game(String name, Pack pack, long timeForRead, long timeForWrite, int maxPlayers, int countCategories, int countQuestions) {
        this.name = name;
        this.pack = pack;
        this.timeForRead = timeForRead;
        this.timeForWrite = timeForWrite;
        this.maxPlayers = maxPlayers;
        this.countCategories = countCategories;
        this.countQuestions = countQuestions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_generator")
    @GenericGenerator(name = "game_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(nullable = false, length = 80)
    private String name;

    private Long startTime;

    private Long endTime;

    private int maxPlayers;

    @OneToMany
    private List<Account> players = new ArrayList<>();

    @ManyToMany
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    private Pack pack;

    private long timeForRead;
    private long timeForWrite;
    private int countCategories;
    private int countQuestions;

    private String difficultTag;

    @Transient
    private Map<Category, Set<Question>> questionsMap = null;

    public void setQuestionsMap(Map<Category, Set<Question>> questionsMap) {
        this.questionsMap = questionsMap;
        if (questionsMap != null) {
            this.questions.clear();
            for (Set<Question> value : questionsMap.values()) {
                this.questions.addAll(value);
            }
        }
    }
}
