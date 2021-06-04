package com.example.model.entity;

public class Activity {
    private long id;
    private String name;
    private Category category;

    private Activity(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public static Activity createActivity(String name, Category category) {
        return new Activity(name, category);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Activity) {
            return name.equals(((Activity) obj).name) && category.equals(((Activity) obj).category);
        }
        return false;
    }
}
