package org.palad.fakeshop.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public enum Category {

    MAN_CLOTHING("mansclothing"),
    WOMAN_CLOTHING("womansclothing"),
    BACKPACK("backpack"),
    CAP("cap"),
    ETC("etc");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getList() {

        List<String> list = new ArrayList<>();

        for(Category category : Category.values()) {
            list.add(category.getValue());
        }

        return list;
    }

    public static String getCategoryByValue(String value) {
        for(Category category : Category.values()) {
            if(value.equals(category.getValue())) {
                return category.name();
            }
        }

        //TODO : null 일시 예외 처리
        //ETC를 통한 default 처리
        return Category.ETC.name();
     }

}
