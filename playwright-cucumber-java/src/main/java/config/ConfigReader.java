package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            // Carga desde el classpath para evitar errores de ruta en Maven
            InputStream is = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            if (is == null) {
                throw new RuntimeException("No se encontró config.properties en resources");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar configuración", e);
        }
    }

    public static String getBrowser() { return properties.getProperty("browser", "chromium"); }

    public static boolean isHeadless() {
        // 1. Intenta leer desde la línea de comandos/sistema (ej. -Dheadless=true)
        String systemHeadless = System.getProperty("headless");
        if (systemHeadless != null) {
            return Boolean.parseBoolean(systemHeadless);
        }
        // 2. Si no hay nada en el sistema, usa lo que dice el archivo config.properties
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static double getSlowMo() { return Double.parseDouble(properties.getProperty("slowMo", "0")); }
    public static int getTimeout() { return Integer.parseInt(properties.getProperty("timeout", "30000")); }
    public static String getScreenshotPath() { return properties.getProperty("screenshot.path", "test-output/screenshots/"); }
}