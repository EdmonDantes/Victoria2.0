/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.core.entity.localization.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liveproduction.victoria.core.entity.localization.impl.LocalizationString;
import ru.liveproduction.victoria.core.entity.localization.manager.ILocalizationStringManager;

import java.io.IOException;

@Component
@Slf4j
public class LocalizationStringJsonSerializer extends JsonSerializer<LocalizationString> {

    private ILocalizationStringManager localizationStringManager;

    @Autowired
    public void setLocalizationStringManager(ILocalizationStringManager localizationStringManager) {
        this.localizationStringManager = localizationStringManager;
    }

    @Override
    public void serialize(LocalizationString value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value.getId() == null) {
            log.error("", new RuntimeException("Can not serialize localization string without id"));
            return;
        }

        gen.writeNumber(value.getId());
    }

}
