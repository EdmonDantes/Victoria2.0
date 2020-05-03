package ru.liveproduction.victoria.core.entity.localization.impl;

import ru.liveproduction.victoria.core.entity.localization.IStoredLocale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StoredLocale implements IStoredLocale {

    public StoredLocale() {}

    public StoredLocale(String javaLanguageTag) {
        this.javaLanguageTag = javaLanguageTag;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String javaLanguageTag;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getJavaLanguageTag() {
        return javaLanguageTag;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IStoredLocale) {
            return id.equals(((IStoredLocale) obj).getId()) || javaLanguageTag.equals(((IStoredLocale) obj).getJavaLanguageTag());
        }
        return false;
    }
}
