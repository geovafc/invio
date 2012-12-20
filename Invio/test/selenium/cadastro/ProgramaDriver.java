package selenium.cadastro;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProgramaDriver {

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
    public void testProgramaDriver() throws Exception {
        driver.get(baseUrl + "/Invio/faces/publico/login/loginInicio.xhtml");
        driver.findElement(By.id("Login:email")).sendKeys("rmsoranso@yahoo.com.br");
        driver.findElement(By.id("Login:senha1")).clear();
        driver.findElement(By.id("Login:senha1")).sendKeys("12345678");
        driver.findElement(By.id("Login:j_idt21")).click();
        driver.findElement(By.id("j_idt17:j_idt22")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:areaNome")).clear();
        driver.findElement(By.id("j_idt31:areaNome")).sendKeys("Agronomia");
        driver.findElement(By.id("j_idt31:j_idt35")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:areaNome")).clear();
        driver.findElement(By.id("j_idt31:areaNome")).sendKeys("Bacharelado em Sistemas de Informacao");
        driver.findElement(By.id("j_idt31:j_idt35")).click();
        driver.findElement(By.xpath("//a[@id='j_idt17:j_idt23']/span[2]")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:instituicaoNome")).clear();
        driver.findElement(By.id("j_idt31:instituicaoNome")).sendKeys("Universidade Federal Rural da Amazonia");
        driver.findElement(By.id("j_idt31:instituicaoSigla")).clear();
        driver.findElement(By.id("j_idt31:instituicaoSigla")).sendKeys("UFRA");
        driver.findElement(By.id("j_idt31:j_idt38")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:instituicaoNome")).clear();
        driver.findElement(By.id("j_idt31:instituicaoNome")).sendKeys("Universidade do Estado do Para");
        driver.findElement(By.id("j_idt31:instituicaoSigla")).clear();
        driver.findElement(By.id("j_idt31:instituicaoSigla")).sendKeys("UEPA");
        driver.findElement(By.id("j_idt31:j_idt38")).click();
        driver.findElement(By.id("j_idt17:j_idt24")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:programaNome")).clear();
        driver.findElement(By.id("j_idt31:programaNome")).sendKeys("Pibic");
        driver.findElement(By.id("j_idt31:instituicaoSelect_label")).click();
        driver.findElement(By.xpath("//div[@id='j_idt31:instituicaoSelect_panel']/div/ul/li")).click();
        driver.findElement(By.id("j_idt31:j_idt39")).click();
        driver.findElement(By.id("form:j_idt33")).click();
        driver.findElement(By.id("j_idt31:programaNome")).clear();
        driver.findElement(By.id("j_idt31:programaNome")).sendKeys("Programa Jovens Talentos");
        driver.findElement(By.id("j_idt31:instituicaoSelect_label")).click();
        driver.findElement(By.xpath("//div[@id='j_idt31:instituicaoSelect_panel']/div/ul/li[2]")).click();
        driver.findElement(By.id("j_idt31:j_idt39")).click();
        driver.findElement(By.id("form:dtPrograma:0:j_idt45")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
        driver.findElement(By.id("j_idt31:cbSalvar")).click();
        driver.findElement(By.id("form:dtPrograma:0:j_idt45")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
        driver.findElement(By.id("j_idt31:cbSalvar")).click();
        driver.findElement(By.id("form:dtPrograma:1:j_idt45")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
        driver.findElement(By.id("j_idt31:cbSalvar")).click();
        driver.findElement(By.id("form:dtPrograma:1:j_idt45")).click();
        driver.findElement(By.id("j_idt31:cbSalvar")).click();
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
