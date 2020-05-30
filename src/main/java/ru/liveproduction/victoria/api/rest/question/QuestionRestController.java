/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.api.rest.question;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liveproduction.victoria.core.entity.questions.impl.Question;
import ru.liveproduction.victoria.core.entity.questions.manager.IQuestionManager;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ru.liveproduction.victoria.Main.ADMIN_TOKEN;
import static ru.liveproduction.victoria.Main.MAPPER;

@Slf4j
@RestController
public class QuestionRestController {

    private IQuestionManager questionManager;

    @Autowired
    public QuestionRestController(IQuestionManager questionManager) {
        this.questionManager = questionManager;
    }

    @RequestMapping(path = "/api/question/getById", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestionFromId(@RequestParam Integer id) {
        return questionManager.getById(id);
    }

    @RequestMapping(path = "/api/question/create", method = RequestMethod.POST)
    @ResponseBody
    public Question createQuestion(@RequestParam(required = false) String key, @RequestBody JsonNode node, HttpServletResponse response) {
        if (!ADMIN_TOKEN.equals(key)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        try {
            Map<String, List<String>> string = MAPPER.readerFor(new TypeReference<Map<String, List<String>>>() {}).readValue(node.get("question"));
            return questionManager.save(node.get("points").asInt(), node.get("categoryId").asInt(), string);
        } catch (IOException e) {
            log.warn("Can not parse json node on request '/api/question/create'.", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(path = "/api/question/getQuestion", method = RequestMethod.GET)
    public String getQuestionString(@RequestParam Integer id, @RequestParam(required = false, defaultValue = "en") String lang) {
        Question question = questionManager.getById(id);
        if (question == null) {
            return null;
        }

        return questionManager.getQuestionString(question, lang);
    }
}
