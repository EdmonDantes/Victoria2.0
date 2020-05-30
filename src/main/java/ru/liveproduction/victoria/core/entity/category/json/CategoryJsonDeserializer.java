/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.category.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.category.manager.ICategoryManager;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;

import java.io.IOException;

@Component
public class CategoryJsonDeserializer extends JsonDeserializer<Category> {

    private ICategoryManager categoryManager;

    @Autowired
    public CategoryJsonDeserializer(ICategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    @Override
    public Category deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode node = p.getCodec().readTree(p);
        if (node instanceof JsonNode) {
            JsonNode root = (JsonNode) node;

            if (node.asToken() == JsonToken.START_OBJECT) {
                Category category = new Category();
                JsonNode name = root.get("name");
                if (name != null) {
                    category.setName(p.getCodec().treeToValue(name, LocalizationString.class));

                    JsonNode description = root.get("description");
                    if (description != null) {
                        category.setDescription(p.getCodec().treeToValue(description, LocalizationString.class));
                    }

                    return categoryManager.save(category);
                }
            } else if (node.asToken() == JsonToken.VALUE_NUMBER_INT) {
                return categoryManager.getById(root.asInt());
            }
        }
        return null;
    }
}
