package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected void navigateTo(String url) {
        page.navigate(url);
    }

    protected void click(String selector) {
        page.click(selector);
    }

    protected void fill(String selector, String text) {
        page.fill(selector, text);
    }

    protected void type(String selector, String text) {
        page.type(selector, text);
    }

    protected String getText(String selector) {
        return page.textContent(selector);
    }

    protected boolean isVisible(String selector) {
        return page.isVisible(selector);
    }

    protected boolean isEnabled(String selector) {
        return page.isEnabled(selector);
    }

    protected void waitForSelector(String selector) {
        page.waitForSelector(selector);
    }

    protected void waitForSelector(String selector, WaitForSelectorState state) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(state));
    }

    protected void selectOption(String selector, String value) {
        page.selectOption(selector, value);
    }

    protected void check(String selector) {
        page.check(selector);
    }

    protected void uncheck(String selector) {
        page.uncheck(selector);
    }

    protected Locator getLocator(String selector) {
        return page.locator(selector);
    }

    protected String getTitle() {
        return page.title();
    }

    protected String getUrl() {
        return page.url();
    }

    protected void goBack() {
        page.goBack();
    }

    protected void goForward() {
        page.goForward();
    }

    protected void reload() {
        page.reload();
    }
}
