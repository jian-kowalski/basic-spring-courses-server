package com.jiankowalski.basicspring.domain;


public class Course {

    public Course(String name, CategoryEnum category, Long id) {
        this.name = name;
        this.category = category;
        this.id = id;
    }

    private String name;

    private CategoryEnum category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
}
