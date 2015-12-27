package com.epam.mentoring.menu;

import com.epam.mentoring.plugin.MenuConstants;
import com.epam.mentoring.service.MenuService;
import com.epam.mentoring.service.MenuServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represent application menu.
 *
 * @author vkrasovsky
 */
public class Menu {
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);
    private List<Item> items = new ArrayList<>();
    private boolean isQuit;

    public Menu() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Executes menu in infinite wile loop. In order to exit type 0.
     * Refreshes menu items with available plugins every time after
     * menu item execution.
     */
    public void execute() {
        while (!isQuit) {
            refreshMenu();
            printMenu();
            Scanner scanner = new Scanner(System.in);
            Integer selected = scanner.nextInt();
            items.get(selected).execute();
        }
    }

    /**
     * Prints menu.
     */
    private void printMenu() {
        LOGGER.info(MenuConstants.DELIMITER);
        LOGGER.info(0 + " : " + items.get(0).getName());
        LOGGER.info(MenuConstants.DELIMITER);
        LOGGER.info(MenuConstants.AVAILABLE_PLUGINS);
        int menuNumber = 1;
        for (int i = 1; i < items.size(); i++) {
            LOGGER.info(menuNumber + " : " + items.get(i).getName());
            menuNumber++;
        }
        LOGGER.info(MenuConstants.DELIMITER);

    }

    /**
     * Refreshes menu.
     */
    private void refreshMenu() {
        items.clear();
        items.add(new Item("QUIT (Quits from application)") {
            @Override
            public void execute() {
                isQuit = true;
            }
        });
        MenuService menuService = new MenuServiceImpl();
        items.addAll(menuService.buildMenuItems());
    }
}
