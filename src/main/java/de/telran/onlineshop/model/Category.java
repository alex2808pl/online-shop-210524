package de.telran.onlineshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Category {
    private long categoryID;
    private String name;

    public Category() {
    }

    public Category(long categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        return categoryID == category.categoryID && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(categoryID);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", name='" + name + '\'' +
                '}';
    }
}
