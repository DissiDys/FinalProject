package com.example.model.entity;

public class Category {
    private long id;
    private String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category createCategory(String name) {
        return new Category(name);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            return name.equals(((Category) obj).name);
        }
        return false;
    }
}
