package com.epam.mentoring.domain;

/**
 * Holds information about loaded plugin.
 *
 * @author vkrasovsky
 */
public class PluginDto {
    private String clazz;
    private String description;
    private String name;
    private int order;

    public PluginDto(String clazz, String description, String name, int order) {
        this.clazz = clazz;
        this.description = description;
        this.name = name;
        this.order = order;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
