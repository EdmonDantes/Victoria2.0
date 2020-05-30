/*
 * Copyright (c) 2020. Ilya Loginov. Please write to dantes2104@gmail.com for get rights for any actions with this code or code's parts.
 */

package ru.liveproduction.victoria.api.rest.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liveproduction.victoria.Main;
import ru.liveproduction.victoria.core.entity.category.impl.Category;
import ru.liveproduction.victoria.core.entity.category.manager.ICategoryManager;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CategoryRestController {

    private final ICategoryManager categoryManager;

    @Autowired
    public CategoryRestController(ICategoryManager categoryManager) {
        this.categoryManager = Objects.requireNonNull(categoryManager, "Category manager can not be null");
    }

    @RequestMapping(path = "/api/category/create", method = RequestMethod.POST)
    @ResponseBody
    public Category createCategory(@RequestParam(required = false) String key, @RequestBody Category category, HttpServletResponse response) {
        if (!Main.ADMIN_TOKEN.equals(key)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return categoryManager.save(category);
    }

    @RequestMapping(path = "/api/category/getName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getCategoryName(@RequestParam Integer id, @RequestParam List<String> lang, HttpServletResponse response) {

        Category category;
        if (id == null || (category = categoryManager.getById(id)) == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Map<String, String> result = new HashMap<>();
        for (String langTag : lang) {
            result.put(langTag, categoryManager.getName(category, langTag));
        }
        return result;
    }

    @RequestMapping(path = "/api/category/getDescription", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getCategoryDescription(@RequestParam Integer id, @RequestParam List<String> lang, HttpServletResponse response) {
        Category category;
        if (id == null || (category = categoryManager.getById(id)) == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Map<String, String> result = new HashMap<>();
        for (String langTag : lang) {
            result.put(langTag, categoryManager.getDescription(category, langTag));
        }
        return result;
    }

    @RequestMapping(path = "/api/category/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Category> getAllCategories() {
        return categoryManager.getAllCategories();
    }

}
