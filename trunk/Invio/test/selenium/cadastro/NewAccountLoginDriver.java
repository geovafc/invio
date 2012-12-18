package selenium.cadastro;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NewAccountLoginDriver {

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
    public void testNewAccountLoginDriver() throws Exception {
        driver.get(baseUrl + "/Invio/");
        driver.findElement(By.id("j_idt24:j_idt28")).click();
        // Warning: assertTextPresent may require manual changes
        driver.findElement(By.id("Cadastro:perfilAcademico_label")).click();
        driver.findElement(By.xpath("//div[@id='Cadastro:perfilAcademico_panel']/div/ul/li[2]")).click();
        driver.findElement(By.id("Cadastro:curriculoNome")).clear();
        driver.findElement(By.id("Cadastro:curriculoNome")).sendKeys("RENAN SORANSO");
        driver.findElement(By.id("Cadastro:cpf")).clear();
        driver.findElement(By.id("Cadastro:cpf")).sendKeys("91264081200");
        driver.findElement(By.id("Cadastro:curriculoGenero_label")).click();
        driver.findElement(By.xpath("//div[@id='Cadastro:curriculoGenero_panel']/div/ul/li[2]")).click();
        driver.findElement(By.id("Cadastro:email")).clear();
        driver.findElement(By.id("Cadastro:email")).sendKeys("rmsoranso@yahoo.com.br");
        driver.findElement(By.id("Cadastro:senha2")).clear();
        driver.findElement(By.id("Cadastro:senha2")).sendKeys("12345678");
        driver.findElement(By.id("Cadastro:j_idt31")).click();
        // Warning: assertTextPresent may require manual changes
        driver.findElement(By.id("Login:email")).sendKeys("rmsoranso@yahoo.com.br");
        driver.findElement(By.id("Login:senha1")).clear();
        driver.findElement(By.id("Login:senha1")).sendKeys("12345678");
        driver.findElement(By.id("Login:j_idt21")).click();
        // Warning: assertTextPresent may require manual changes
        driver.findElement(By.id("Codigo:codigoConfirmacao")).clear();
        driver.findElement(By.id("Codigo:codigoConfirmacao")).sendKeys("123");
        driver.findElement(By.id("Codigo:j_idt20")).click();
        // Warning: assertTextPresent may require manual changes
        assertTrue(driver.getPageSource().contains("Seja bem vindo(a) ao sistema Invio RENAN SORANSO !"));
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
