/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.localization.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LocalizationStringJsonDeserializer extends JsonDeserializer<LocalizationString> {

    private ILocalizationStringManager localizationStringManager;

    @Autowired
    public void setLocalizationStringManager(ILocalizationStringManager localizationStringManager) {
        this.localizationStringManager = localizationStringManager;
    }

    @Override
    public LocalizationString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode node = p.getCodec().readTree(p);
        if (node instanceof JsonNode) {
            if (node.asToken() == JsonToken.START_OBJECT) {
                Map<String, String> result = new HashMap<>();
                ((JsonNode) node).fields().forEachRemaining(entry -> {
                    result.put(entry.getKey(), entry.getValue().asText());
                });
                return localizationStringManager.save(result);
            } else if (node.asToken() == JsonToken.VALUE_NUMBER_INT) {
                return localizationStringManager.getById(((JsonNode) node).asInt());
            }
        }
        return null;
    }
}
