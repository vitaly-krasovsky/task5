package com.epam.mentoring.service;

import com.epam.mentoring.menu.Item;

import java.util.List;

/**
 * Used for dynamic building of menu items.
 *
 * @author vkrasovsky
 */
public interface MenuService {
    /**
     * Builds menu items.
     *
     * @return available plugins
     */
    List<Item> buildMenuItems();
}
