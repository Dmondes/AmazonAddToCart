import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AmazonAddToCart 
{

    public static void main(String[] args) 
    {

        // Initialize Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // Open Amazon website
            driver.get("https://www.amazon.sg");

            // Click on Sign In
            WebElement signInButton = driver.findElement(By.id("nav-link-accountList"));
            signInButton.click();

            // Read username and password from the text file
            BufferedReader reader = new BufferedReader(new FileReader("/Users/desmond/Desktop/Amazonaddtocart/src/AmazonPassword.txt"));
            String line = reader.readLine();

            if (line != null)
            {
                // Split the line to extract username and password
                String[] credentials = line.split(" ");
                String username = credentials[0];
                String password = credentials[1];

                // Locate username field and enter username
                WebElement usernameField = driver.findElement(By.id("ap_email"));
                usernameField.sendKeys(username);
                WebElement ContinueButton = driver.findElement(By.id("continue"));
                ContinueButton.click();

                // Locate password field and enter password
                WebElement passwordField = driver.findElement(By.id("ap_password"));
                passwordField.sendKeys(password);

                // Click on Login button
                WebElement loginButton = driver.findElement(By.id("signInSubmit"));
                loginButton.click();
                
                // Search for macbook air m2
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
                searchBox.sendKeys("macbook air m2 15");
                searchBox.submit();

                // Click on a specific MacBook Air M2 product
                WebElement imageElement = driver.findElement(By.xpath("//img[contains(@alt, 'Apple 2023 MacBook Air laptop with M2 chip')]")); 
                imageElement.click();

                // Locate and click on the "Add to Cart" button
                WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
                addToCartButton.click();

                // Click on the cart
                WebElement cartButton = driver.findElement(By.id("nav-cart"));
                cartButton.click();
            } 
            else 
            {
                System.out.println("No credentials found in the file.");
            }

            reader.close();

        }
        catch (IOException e) //catch input output error and prints the trace
        {
            e.printStackTrace();
        } 
        finally 
        {
            // Close the browser
        	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.quit();
        }
    }
}
