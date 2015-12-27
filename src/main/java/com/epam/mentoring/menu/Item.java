package com.epam.mentoring.menu;

/**
 * Represents menu item.
 *
 * @author vkrasovsky
 */
public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute();
}
