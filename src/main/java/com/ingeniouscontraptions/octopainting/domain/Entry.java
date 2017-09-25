package com.ingeniouscontraptions.octopainting.domain;

import java.io.Serializable;

/**
 * The entry.
 * An entry has a name and is submitted in a category.
 * 
 * @author Arthur Noseda
 */
public class Entry implements Serializable {

    private final String name;

    private final Category category;

    /**
     * Constructs an entry.
     * 
     * @param name     the name of the entry
     * @param category the category in which the entry is submitted
     */
    public Entry(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    /**
     * Returns the name of the entry.
     * 
     * @return the name of the entry
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category in which the entry is submitted.
     * 
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Entry{name=" + name + ", category=" + category + "}";
    }

}
