/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.account.impl.Account;
import ru.liveproduction.victoria.core.entity.game.impl.Game;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {


}
