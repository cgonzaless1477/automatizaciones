package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.PlaywrightFactory;

import static org.junit.jupiter.api.Assertions.*;

public class HomeSteps {
    
    private HomePage homePage;

    public HomeSteps() {
        this.homePage = new HomePage(PlaywrightFactory.getPage());
    }

    @Given("el usuario está en la página principal")
    public void elUsuarioEstaEnLaPaginaPrincipal() {
        assertTrue(homePage.isUserLoggedIn(), 
            "El usuario no está en la página principal");
    }

    @When("el usuario busca {string}")
    public void elUsuarioBusca(String searchTerm) {
        homePage.searchFor(searchTerm);
    }

    @When("el usuario hace clic en su perfil")
    public void elUsuarioHaceClicEnSuPerfil() {
        homePage.clickUserProfile();
    }

    @When("el usuario cierra sesión")
    public void elUsuarioCierraSesion() {
        homePage.logout();
    }

    @Then("el usuario debería ver el mensaje de bienvenida")
    public void elUsuarioDeberiaVerElMensajeDeBienvenida() {
        assertTrue(homePage.isWelcomeMessageDisplayed(), 
            "El mensaje de bienvenida no está visible");
    }

    @Then("el mensaje de bienvenida debería contener {string}")
    public void elMensajeDeBienvenidaDeberiaContener(String expectedText) {
        String welcomeMessage = homePage.getWelcomeMessage();
        assertTrue(welcomeMessage.contains(expectedText), 
            "El mensaje de bienvenida no contiene: " + expectedText);
    }

    @Then("el usuario debería ver {int} elementos en el menú")
    public void elUsuarioDeberiaVerElementosEnElMenu(int expectedCount) {
        int actualCount = homePage.getMenuItemsCount();
        assertEquals(expectedCount, actualCount, 
            "La cantidad de elementos del menú no coincide");
    }
}
