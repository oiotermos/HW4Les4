package myprojects.automation.assignment4;


import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.navigate().to(Properties.getBaseAdminUrl());
        WebElement loginInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("passwd"));
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        passwordInput.submit();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
        WebElement catalog = driver.findElement(By.cssSelector("#subtab-AdminCatalog"));
        WebElement product = driver.findElement(By.cssSelector("#subtab-AdminProducts"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catalog).perform();
        actions.moveToElement(product).perform();
        product.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".m-b-2")));
        WebElement newProduct = driver.findElement(By.cssSelector(".m-b-2"));
        newProduct.click();

    }

    public void createProduct(ProductData newProduct) {
        WebElement nameProduct = driver.findElement(By.cssSelector("#form_step1_name_1"));
        nameProduct.sendKeys(newProduct.getName());
        WebElement quantity = driver.findElement(By.cssSelector("#form_step1_qty_0_shortcut"));
        quantity.sendKeys("\b");
        quantity.sendKeys(newProduct.getQty().toString());
        WebElement price = driver.findElement(By.cssSelector("#form_step1_price_shortcut"));
        price.sendKeys("\b\b\b\b\b\b\b\b");
        price.sendKeys(newProduct.getPrice());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".switch-input")));
        WebElement activate = driver.findElement(By.cssSelector(".switch-input"));
        activate.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn btn-primary save uppercase']")));
        WebElement save = driver.findElement(By.cssSelector("[class='btn btn-primary save uppercase']"));
        save.click();

    }

    public void check(ProductData newProduct){
        driver.navigate().to(Properties.getBaseUrl());
        WebElement allProduct = driver.findElement(By.cssSelector("[class='all-product-link pull-xs-left pull-md-right h4']"));
        allProduct.click();
        WebElement search = driver.findElement(By.cssSelector(".ui-autocomplete-input"));
        search.sendKeys(newProduct.getName());
        WebElement searchInput = driver.findElement(By.cssSelector("[class='material-icons search']"));
        searchInput.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='h3 product-title']")));
        WebElement quickView = driver.findElement(By.cssSelector("[class='quick-view']"));
        WebElement navigate = driver.findElement(By.cssSelector("[class='h3 product-title']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(navigate).perform();
        actions.moveToElement(quickView).perform();
        quickView.click();

        WebElement checkName = driver.findElement(By.cssSelector("[class='h1']"));
        WebElement checkPrice = driver.findElement(By.cssSelector("div[class='current-price'] span[itemprop='price']"));
        WebElement checkQuantity = driver.findElement(By.cssSelector("#quantity_wanted"));

        if(equals(checkName.getText()) == equals(newProduct.getName())){
            System.out.println("SUCCESSFUL NAME CHECK!!!");
            if(equals(checkPrice.getText()) == equals(newProduct.getPrice())){
                System.out.println("SUCCESSFUL PRICE CHECK!!!");
                if(equals(checkQuantity.getText()) == equals(newProduct.getQty())){
                    System.out.println("SUCCESSFUL QUANTITY CHECK!!!");
                }
                else{
                    System.out.println("QUANTITY CHECK ERROR");
                }
            }
            else{
                System.out.println("PRICE CHECK ERROR");
            }
        }
        else{
            System.out.println("NAME CHECK ERROR");
        }



    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        // TODO implement generic method to wait until page content is loaded

        // wait.until(...);
        // ...
    }
}
