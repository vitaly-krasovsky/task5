package com.epam.mentoring;

import com.epam.mentoring.menu.Menu;

/**
 * Main application.
 *
 * @author vkrasovsky
 */
public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.execute();
    }
}
