package com.jiankowalski.basicspring.domain;

public enum CategoryEnum {

    FRONT_END("front-end"),
    BACK_END("back-end");

    private final String description;

    CategoryEnum(String description) {
        this.description = description;
    }
}
