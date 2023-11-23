package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebDeshabilitarCurso {
    ChromeDriver driver;
    WebDriverWait wait;
    String url = "http://127.0.0.1:8000/login";
    String tituloCursoTemp;

    @BeforeTest
    public void invocarNavegador() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\mikes\\OneDrive\\Documentos\\TestDSI\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10); // Espera hasta 10 segundos
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterTest
    public void cerrarNavegador() {
        driver.quit();
    }

    @Test(priority = 0)
    public void verificarLoginAseisNews() {
        WebElement userId = driver.findElement(By.name("email"));
        WebElement userPassword = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));

        userId.sendKeys("cm18064@ues.edu.sv");
        userPassword.sendKeys("Minerva.23");
        loginButton.click();
        // Esperar a que un elemento característico de la página principal esté presente
        WebElement elementoPrincipal = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logo-sidebar1")));
    }
	
    @Test(priority = 1)
    public void deshabilitarCurso() throws InterruptedException{
    	driver.get("http://127.0.0.1:8000/AdministrarCursos/indexCursosPublicados");
    	Thread.sleep(1200);
    	WebElement deshabilitarButton = driver.findElement(By.className("btnDeshabilitarCurso"));
    	deshabilitarButton.click();
    	Thread.sleep(700);
    	driver.findElement(By.cssSelector(".swal2-confirm.swal2-styled.swal2-default-outline")).click();
        Thread.sleep(700);
        driver.findElement((By.cssSelector(".swal2-confirm.swal2-styled"))).click();;
        Thread.sleep(1000);
    }
    
}
