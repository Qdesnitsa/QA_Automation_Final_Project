package by.it_academy.onliner.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);
    private static final String CONFIG_PROPERTIES_PATH = "src/main/resources/config.properties";
    private static final String ENDPOINTS_PROPERTIES_PATH = "src/main/resources/endpoints.properties";

    public static URL getConfigURL(String url) {
        try {
            return new URL(getConfigProperty(url));
        } catch (MalformedURLException ex) {
            LOG.info("Malformed URL has occurred");
            return null;
        }
    }

    public static String getConfigProperty(String property) {
        return getStringFromPropertiesFile(CONFIG_PROPERTIES_PATH, property);
    }

    public static String getEndpoint(String property) {
        return getStringFromPropertiesFile(ENDPOINTS_PROPERTIES_PATH, property);
    }

    private static String getStringFromPropertiesFile(String path, String property) {
        try (InputStream input = new FileInputStream(path)) {
            Properties appProps = new Properties();
            appProps.load(input);
            return appProps.getProperty(property);
        } catch (IOException ex) {
            LOG.info("Failed to find properties file");
            return null;
        }
    }
}
