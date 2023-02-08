package pl.robalmeister.goxy.chat.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private final FileConfiguration fileConfiguration;

    public Configuration(FileConfiguration fileConfiguration, File file) {
        this.fileConfiguration = fileConfiguration;
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String key) {
        return fileConfiguration.getString(key);
    }

    public boolean getBoolean(String key) {
        return fileConfiguration.getBoolean(key);
    }
}
