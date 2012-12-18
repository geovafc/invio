package selenium.cadastro;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InstituicaoDriver {

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
    public void testInstituicaoDriver() throws Exception {
        driver.get(baseUrl + "/Invio/faces/publico/login/loginInicio.xhtml");
        driver.findElement(By.id("Login:email")).sendKeys("rmsoranso@yahoo.com.br");
        driver.findElement(By.id("Login:senha1")).clear();
        driver.findElement(By.id("Login:senha1")).sendKeys("12345678");
        driver.findElement(By.id("Login:j_idt21")).click();
        driver.findElement(By.xpath("//a[@id='j_idt17:j_idt23']/span[2]")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:instituicaoNome")).clear();
        driver.findElement(By.id("j_idt31:instituicaoNome")).sendKeys("Universidade Federal da Amazonia");
        driver.findElement(By.id("j_idt31:instituicaoSigla")).clear();
        driver.findElement(By.id("j_idt31:instituicaoSigla")).sendKeys("UFPA");
        driver.findElement(By.id("j_idt31:j_idt38")).click();
        driver.findElement(By.id("form:dtInstituicao:0:j_idt40")).click();
        driver.findElement(By.id("j_idt31:instituicaoNome")).clear();
        driver.findElement(By.id("j_idt31:instituicaoNome")).sendKeys("Universidade do Esstado do Para");
        driver.findElement(By.id("j_idt31:instituicaoSigla")).clear();
        driver.findElement(By.id("j_idt31:instituicaoSigla")).sendKeys("UEPA");
        driver.findElement(By.id("j_idt31:j_idt38")).click();
        driver.findElement(By.id("form:dtInstituicao:0:j_idt41")).click();
        // Warning: assertTextPresent may require manual changes
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Deseja realmente Exluir[\\s\\S][\\s\\S]*$"));
        driver.findElement(By.id("j_idt31:j_idt36")).click();
        // Warning: verifyTextPresent may require manual changes
        try {
            assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Sem registro para a exibição\\.[\\s\\S]*$"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
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
