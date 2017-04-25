package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {

    private static String login = "webinar.test@gmail.com";
    private static String password = "Xcg7299bnSmMuRLp9ITw";

    @Test
    public void createNewProduct() {
        // TODO implement test for product creation

        actions.login(this.login, this.password);
        ProductData productData = ProductData.generate();
        actions.createProduct(productData);
        actions.check(productData);

    }

    // TODO implement logic to check product visibility on website
}
