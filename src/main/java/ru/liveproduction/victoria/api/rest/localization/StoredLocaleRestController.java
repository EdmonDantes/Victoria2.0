/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.api.rest.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liveproduction.victoria.core.entity.localization.impl.StoredLocale;
import ru.liveproduction.victoria.core.entity.localization.manager.IStoredLocaleManager;

import java.util.List;

@RestController
public class StoredLocaleRestController {

    private IStoredLocaleManager storedLocaleManager;

    @Autowired
    public StoredLocaleRestController(IStoredLocaleManager storedLocaleManager) {
        this.storedLocaleManager = storedLocaleManager;
    }

    @RequestMapping(path = "/api/localization/getAllSupportLocale", method = RequestMethod.GET)
    @ResponseBody
    public List<StoredLocale> getAllSupportLocale(){
        return storedLocaleManager.getAllStoredLocales();
    }

    @RequestMapping(path = "/api/localization/getAllSupportLocaleByLang", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<StoredLocale> getAllSupportLocaleByLang(@RequestParam String lang) {
        return storedLocaleManager.getUsingLocales(lang);
    }

    @RequestMapping(path = "/api/localization/getSupportLocaleByLang", method = RequestMethod.GET)
    @ResponseBody
    public StoredLocale getSupportLocaleByLang(@RequestParam String lang) {
        return storedLocaleManager.getStoredLocaleFrom(lang);
    }
}
