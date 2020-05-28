package ru.liveproduction.victoria.core.entity.questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {}