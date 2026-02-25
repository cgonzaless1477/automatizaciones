package runners;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
// Esta línea le dice a JUnit dónde están tus archivos .feature
@SelectClasspathResource("features")
// GLUE: Aquí está el truco. "steps" buscará en steps y en CUALQUIER subcarpeta (como steps.mercadolibre)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value =
        "pretty," +
                "html:target/cucumber-reports/cucumber.html," +
                "json:target/cucumber-reports/cucumber.json," +
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:")
// Eliminamos la redundancia de FEATURES_PROPERTY_NAME para evitar conflictos
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@mercadolibre")
public class TestRunner {
    // No se necesita código aquí
}