/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.api.rest.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liveproduction.victoria.Main;
import ru.liveproduction.victoria.core.entity.pack.impl.Pack;
import ru.liveproduction.victoria.core.entity.pack.manager.IPackManager;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PackRestController {

    private final IPackManager packManager;

    @Autowired
    public PackRestController(IPackManager packManager) {
        this.packManager = packManager;
    }

    @RequestMapping(path = "api/pack/create", method = RequestMethod.POST)
    @ResponseBody
    public Pack createPack(@RequestBody Pack pack, @RequestParam(required = false) String key, HttpServletResponse response) {
        if (!Main.ADMIN_TOKEN.equals(key)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        return packManager.save(pack);
    }

    @RequestMapping(path = "api/pack/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addQuestionToPack(@RequestParam Integer packId, @RequestParam Integer questionId, @RequestParam(required = false) String key, HttpServletResponse response) {
        if (!Main.ADMIN_TOKEN.equals(key)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        return packManager.addQuestion(packId, questionId);
    }

    @RequestMapping(path = "api/pack/getById", method = RequestMethod.GET)
    @ResponseBody
    public Pack getById(@RequestParam Integer packId) {
        return packManager.getById(packId);
    }

    @RequestMapping(path = "api/pack/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Pack> getAll() {
        return packManager.getAllPacks();
    }

}
