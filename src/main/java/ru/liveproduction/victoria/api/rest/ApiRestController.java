/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.api.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static ru.liveproduction.victoria.Main.MAPPER;

@RestController
public class ApiRestController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT}, path = "/api")
    @ResponseBody
    public JsonNode getStatus() {
        ObjectNode objectNode = MAPPER.createObjectNode();
        objectNode.put("Enable", true);
        objectNode.put("Status", "API is working");
        return objectNode;
    }

}
