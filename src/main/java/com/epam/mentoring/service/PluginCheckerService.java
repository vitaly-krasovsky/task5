package com.epam.mentoring.service;

import com.epam.mentoring.domain.PluginDto;

import java.util.List;

/**
 * Used for loading available plugins from plugin folder.
 *
 * @author vkrasovsky
 */
public interface PluginCheckerService {
    /**
     * Retrieve all available plugins.
     *
     * @return available plugins
     */
    List<PluginDto> loadAvailablePlugins();
}
