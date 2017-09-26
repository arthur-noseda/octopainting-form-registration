package com.ingeniouscontraptions.octopainting.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Category rhs = (Category) obj;
        return new EqualsBuilder()
                .append(name, rhs.name)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Category{name=" + name + "}";
    }

}
