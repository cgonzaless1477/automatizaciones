package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    
    // Locators
    private final String usernameInput = "#username";
    private final String passwordInput = "#password";
    private final String loginButton = "button[type='submit']";
    private final String errorMessage = ".error-message";
    private final String successMessage = ".success-message";

    public LoginPage(Page page) {
        super(page);
    }

    public void navigateToLoginPage(String url) {
        navigateTo(url);
    }

    public void enterUsername(String username) {
        fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        fill(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isVisible(successMessage);
    }

    public String getPageTitle() {
        return getTitle();
    }
}
