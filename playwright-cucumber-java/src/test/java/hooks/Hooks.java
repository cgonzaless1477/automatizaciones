package hooks;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PlaywrightFactory;
import utils.ScreenshotHelper;

public class Hooks {

    @Before
    public void setUp() {
        PlaywrightFactory.initializeBrowser();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Tomar screenshot si el escenario falla
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotHelper.takeScreenshotAsBytes(PlaywrightFactory.getPage());
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        
        PlaywrightFactory.quitBrowser();
    }
}
