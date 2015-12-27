package com.epam.mentoring.service;

import com.epam.mentoring.domain.PluginDto;
import com.epam.mentoring.menu.Item;
import com.epam.mentoring.plugin.MenuConstants;
import com.epam.mentoring.plugin.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Default implementation of MenuService.
 *
 * @author vkrasovsky
 */
public class MenuServiceImpl implements MenuService {
    private static final Logger LOGGER = LogManager.getLogger(PluginCheckerServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> buildMenuItems() {
        PluginCheckerService pluginCheckerService = new PluginCheckerServiceImpl();
        List<PluginDto> availablePlugins = pluginCheckerService.loadAvailablePlugins();

        List<Item> items = new ArrayList<>();
        for (final PluginDto pluginDto : availablePlugins) {
            items.add(new Item(pluginDto.getName() + " " + pluginDto.getDescription()) {
                @Override
                public void execute() {
                    try {
                        final URLClassLoader pluginLoader = new URLClassLoader(new URL[]{new File(PluginCheckerServiceImpl.PLUGIN_DIRECTORY).toURI().toURL()});
                        Class<?> clazz = pluginLoader.loadClass(pluginDto.getClazz());
                        Plugin plugin = (Plugin) clazz.newInstance();
                        LOGGER.info(MenuConstants.TYPE_INPUT_STRING);
                        Scanner scanner = new Scanner(System.in);
                        String input = scanner.next();
                        plugin.processInput(input);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        LOGGER.debug("Error while loading plugin class", e.getMessage());
                    } catch (MalformedURLException e) {
                        LOGGER.debug("Incorrect plugin directory", e.getMessage());
                    }
                }
            });
        }
        return items;
    }
}
