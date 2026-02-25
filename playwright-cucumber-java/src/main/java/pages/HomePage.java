package pages;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    
    // Locators
    private final String welcomeMessage = "h1.welcome";
    private final String searchBox = "input[name='search']";
    private final String searchButton = "button.search-btn";
    private final String userProfile = ".user-profile";
    private final String logoutButton = "a.logout";
    private final String menuItems = ".menu-item";

    public HomePage(Page page) {
        super(page);
    }

    public boolean isWelcomeMessageDisplayed() {
        return isVisible(welcomeMessage);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    public void searchFor(String searchTerm) {
        fill(searchBox, searchTerm);
        click(searchButton);
    }

    public void clickUserProfile() {
        click(userProfile);
    }

    public void logout() {
        click(logoutButton);
    }

    public boolean isUserLoggedIn() {
        return isVisible(userProfile);
    }

    public int getMenuItemsCount() {
        return getLocator(menuItems).count();
    }

    public void clickMenuItem(int index) {
        getLocator(menuItems).nth(index).click();
    }
}
