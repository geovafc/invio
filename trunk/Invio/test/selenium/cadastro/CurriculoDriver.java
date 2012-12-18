package selenium.cadastro;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CurriculoDriver {

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
    public void testCurriculoDriver() throws Exception {
        driver.get(baseUrl + "/Invio/faces/publico/login/loginInicio.xhtml");
        driver.get(baseUrl + "/Invio/faces/publico/login/loginInicio.xhtml");
        driver.findElement(By.id("Login:email")).sendKeys("rmsoranso@yahoo.com.br");
        driver.findElement(By.id("Login:senha1")).clear();
        driver.findElement(By.id("Login:senha1")).sendKeys("12345678");
        driver.findElement(By.id("Login:j_idt21")).click();
        driver.findElement(By.xpath("//a[@id='j_idt17:j_idt25']/span[2]")).click();
        driver.findElement(By.id("form:dtCurriculo:0:j_idt38")).click();
        driver.findElement(By.id("j_idt31:nascimentoCal_input")).click();
        driver.findElement(By.id("j_idt31:nascimentoCal_input")).clear();
        driver.findElement(By.id("j_idt31:nascimentoCal_input")).sendKeys("07/10/1991");
        driver.findElement(By.id("j_idt31:genero_label")).click();
        driver.findElement(By.xpath("//div[@id='j_idt31:genero_panel']/div/ul/li[2]")).click();
        driver.findElement(By.id("j_idt31:cpf")).click();
        driver.findElement(By.id("j_idt31:matricula")).clear();
        driver.findElement(By.id("j_idt31:matricula")).sendKeys("20106044");
        driver.findElement(By.id("j_idt31:lattes")).clear();
        driver.findElement(By.id("j_idt31:lattes")).sendKeys("teste");
        driver.findElement(By.id("j_idt31:curso")).clear();
        driver.findElement(By.id("j_idt31:curso")).sendKeys("BIA2010");
        driver.findElement(By.id("j_idt31:j_idt32_next")).click();
        driver.findElement(By.id("j_idt31:bairro")).clear();
        driver.findElement(By.id("j_idt31:bairro")).sendKeys("Marco");
        driver.findElement(By.id("j_idt31:cidade")).clear();
        driver.findElement(By.id("j_idt31:cidade")).sendKeys("Belem");
        driver.findElement(By.id("j_idt31:estado")).clear();
        driver.findElement(By.id("j_idt31:estado")).sendKeys("Paraa");
        driver.findElement(By.id("j_idt31:pais")).clear();
        driver.findElement(By.id("j_idt31:pais")).sendKeys("Brasil");
        driver.findElement(By.id("j_idt31:j_idt32_next")).click();
        driver.findElement(By.id("j_idt31:j_idt32_next")).click();
        driver.findElement(By.id("j_idt31:j_idt94")).click();
        driver.findElement(By.id("form:dtCurriculo:0:j_idt43")).click();
        driver.findElement(By.id("Periodico:Titulo")).clear();
        driver.findElement(By.id("Periodico:Titulo")).sendKeys("Renan");
        driver.findElement(By.id("Periodico:Autor")).clear();
        driver.findElement(By.id("Periodico:Autor")).sendKeys("Soranso");
        driver.findElement(By.id("Periodico:Revista")).clear();
        driver.findElement(By.id("Periodico:Revista")).sendKeys("MacMgazine");
        driver.findElement(By.id("Periodico:Volume")).clear();
        driver.findElement(By.id("Periodico:Volume")).sendKeys("2010");
        driver.findElement(By.id("Periodico:Pagina")).clear();
        driver.findElement(By.id("Periodico:Pagina")).sendKeys("10");
        driver.findElement(By.id("Periodico:btn_add")).click();
        driver.findElement(By.id("Periodico:Titulo")).clear();
        driver.findElement(By.id("Periodico:Titulo")).sendKeys("Junio");
        driver.findElement(By.id("Periodico:Autor")).clear();
        driver.findElement(By.id("Periodico:Autor")).sendKeys("Enemias");
        driver.findElement(By.id("Periodico:Revista")).clear();
        driver.findElement(By.id("Periodico:Revista")).sendKeys("Samsung Ruim");
        driver.findElement(By.id("Periodico:Volume")).clear();
        driver.findElement(By.id("Periodico:Volume")).sendKeys("001");
        driver.findElement(By.id("Periodico:Pagina")).clear();
        driver.findElement(By.id("Periodico:Pagina")).sendKeys("400");
        driver.findElement(By.id("Periodico:j_idt41")).click();
        driver.findElement(By.id("Periodico:periodicosTabela:0:j_idt70")).click();
        driver.findElement(By.id("j_idt31:j_idt36")).click();
        driver.findElement(By.id("j_idt32")).click();
        driver.findElement(By.id("form:dtCurriculo:0:j_idt44")).click();
        driver.findElement(By.id("form:dtCurriculo:0:j_idt44")).click();
        driver.findElement(By.id("Livro:Titulo")).clear();
        driver.findElement(By.id("Livro:Titulo")).sendKeys("Junior");
        driver.findElement(By.id("Livro:Autor")).clear();
        driver.findElement(By.id("Livro:Autor")).sendKeys("Junior");
        driver.findElement(By.id("Livro:Capitulo")).clear();
        driver.findElement(By.id("Livro:Capitulo")).sendKeys("20");
        driver.findElement(By.id("Livro:btn_add")).click();
        driver.findElement(By.id("Livro:livrosTabela:1:j_idt64")).click();
        driver.findElement(By.id("j_idt31:j_idt36")).click();
        driver.findElement(By.id("j_idt32")).click();
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
