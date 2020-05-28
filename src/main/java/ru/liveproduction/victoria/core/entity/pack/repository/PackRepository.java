/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {}
