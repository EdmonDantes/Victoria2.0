/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.game.manager.impl;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Data
public class Action {

    /**
     * 0 - none
     * 1 - get current player
     * 2 - get current question
     * 3 - get current time for read
     * 4 - get current time for write
     * 5 - check answer
     * 6 - choose question
     */
    private int id;

    private Map<String, Object> params = new HashMap<>();

    private CompletableFuture future = new CompletableFuture();

    public Action(int id) {
        this.id = id;
    }
}
