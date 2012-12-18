package selenium.cadastro;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AreaDriver {

    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8084/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testNewAreaDriver() throws Exception {
        driver.get(baseUrl + "/Invio/faces/publico/login/loginInicio.xhtml");
        driver.findElement(By.id("Login:email")).sendKeys("rmsoranso@yahoo.com.br");
        driver.findElement(By.id("Login:senha1")).clear();
        driver.findElement(By.id("Login:senha1")).sendKeys("12345678");
        driver.findElement(By.id("Login:j_idt21")).click();
        driver.findElement(By.id("j_idt17:j_idt22")).click();
        // Warning: assertTextPresent may require manual changes
        assertTrue(driver.getPageSource().contains("Lista de Áreas"));
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:areaNome")).clear();
        driver.findElement(By.id("j_idt31:areaNome")).sendKeys("Agronomia");
        driver.findElement(By.id("j_idt31:j_idt35")).click();
        // Warning: assertTextPresent may require manual changes
        assertTrue(driver.getPageSource().contains("Agronomia"));
        driver.findElement(By.id("form:dtArea:0:j_idt38")).click();
        driver.findElement(By.id("j_idt31:areaNome")).clear();
        driver.findElement(By.id("j_idt31:areaNome")).sendKeys("Engenharia Florestal");
        driver.findElement(By.id("j_idt31:j_idt35")).click();
        driver.findElement(By.id("form:dtArea:0:j_idt39")).click();
        // Warning: assertTextPresent may require manual changes
        assertTrue(driver.getPageSource().contains("Deseja realmente Exluir?"));
        driver.findElement(By.id("j_idt31:j_idt36")).click();
        // Warning: assertTextPresent may require manual changes
        assertTrue(driver.getPageSource().contains("Lista de Áreas"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
