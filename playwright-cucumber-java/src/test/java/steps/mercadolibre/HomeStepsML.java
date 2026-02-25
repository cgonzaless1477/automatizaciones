package steps.mercadolibre;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import pages.ResultsPageML;

public class HomeStepsML {
    // Obtenemos la instancia de la página (esto depende de tu PlaywrightFactory)
    private Page page = PlaywrightFactory.getPage();
    private ResultsPageML resultsPage = new ResultsPageML(page);

    @Given("que el usuario accede a la pagina de mercadolibre")
    public void queElUsuarioAccedeALaPaginaDeMercadolibre() {

        page.navigate("https://www.mercadolibre.com.pe");
    }

    @When("realiza la busqueda de un producto {string}")
    public void realizaLaBusquedaDeUnProducto(String producto) {
        // Localizamos el input por ID, limpiamos, escribimos el producto y presionamos Enter
        page.locator("#cb1-edit").fill(producto);
        page.locator("#cb1-edit").press("Enter");
        resultsPage.manejarPopUpUbicacion();
    }

    @Then("se muestra resultado del producto buscado")
    public void seMuestraResultadoDelProductoBuscado() {
        resultsPage.seleccionarProductoMasBarato();


    }
}
