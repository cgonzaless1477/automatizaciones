package utils; // IMPORTANTE: Debe coincidir con la carpeta

import com.microsoft.playwright.*;
import config.ConfigReader;

public class PlaywrightFactory {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void initializeBrowser() {
        playwright = Playwright.create();

        // 1. Opciones de lanzamiento
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(true) // Forzado para K8s
                .setArgs(java.util.Arrays.asList(
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--disable-gpu"
                ));

        // 2. Selección del navegador (Aseguramos que 'browser' deje de ser null)
        String browserName = ConfigReader.getBrowser().toLowerCase();
        switch (browserName) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            default:
                browser = playwright.chromium().launch(options);
                break;
        }

        // 3. Opciones de Contexto con User Agent (Aquí es donde daba el error)
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");

        // 4. Crear contexto y página
        context = browser.newContext(contextOptions);
        page = context.newPage();
        page.setDefaultTimeout(ConfigReader.getTimeout());
    }
    public static Page getPage() {
        if (page == null) initializeBrowser();
        return page;
    }

    public static void quitBrowser() {
        if (playwright != null) playwright.close();
        page = null; // Limpiar referencia
    }
}