package com.ingeniouscontraptions.octopainting.domain;

import java.io.Serializable;

/**
 * The category for an entry.
 * I chose to model categories as a class rather than an enum, as categories are updated every now
 * and then.
 * 
 * @author Arthur Noseda
 */
public class Category implements Serializable {

    private final String name;

    /**
     * Constructs a category.
     * 
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the category.
     * 
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{name=" + name + "}";
    }

}
