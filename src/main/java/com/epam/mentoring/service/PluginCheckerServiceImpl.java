package com.epam.mentoring.service;

import com.epam.mentoring.domain.PluginDto;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Default implementation of PluginCheckerService.
 *
 * @author vkrasovsky
 */
public class PluginCheckerServiceImpl implements PluginCheckerService {
    private static final Logger LOGGER = LogManager.getLogger(PluginCheckerServiceImpl.class);

    public static final String PLUGIN_DIRECTORY = "/home/kras/tmp/task5/plugins";
    private static final String PLUGIN_EXTENSION = ".jar";
    private static final String PLUGIN_PREFIX = "jar:file:";
    private static final String PLUGIN_CONFIG_FILE = "!/plugin-config";

    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_CLAZZ = "clazz";
    private static final String PROPERTY_DESCRIPTION = "description";


    @Override
    public List<PluginDto> loadAvailablePlugins() {
        List<PluginDto> availablePlugins = new ArrayList<>();

        FluentIterable<File> iterable = Files.fileTreeTraverser()
                .breadthFirstTraversal(new File(PLUGIN_DIRECTORY))
                .filter(new Predicate<File>() {
                    @Override
                    public boolean apply(File input) {
                        return input.getName().endsWith(PLUGIN_EXTENSION);
                    }
                });

        int order = 1;

        for (File file : iterable) {
            Properties properties = new Properties();
            try {
                String config = PLUGIN_PREFIX + file.getPath() + PLUGIN_CONFIG_FILE;
                URL url = new URL(config);
                properties.load(url.openStream());
                availablePlugins.add(new PluginDto(properties.getProperty(PROPERTY_CLAZZ), properties.getProperty(PROPERTY_DESCRIPTION), properties.getProperty(PROPERTY_NAME), order));
            } catch (IOException e) {
                LOGGER.debug("Error while loading plugins", e.getMessage());
            }
            order++;
        }

        return availablePlugins;
    }
}
