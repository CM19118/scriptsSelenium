package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebEliminarComentario {
    ChromeDriver driver;
    WebDriverWait wait;
    String url = "http://127.0.0.1:8000/login";
    String tituloCursoTemp;

    @BeforeTest
    public void invocarNavegador() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\mikes\\OneDrive\\Documentos\\TestDSI\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3); // Espera hasta 10 segundos
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
    public void eliminarComentario() throws InterruptedException{
		Thread.sleep(300);
    	driver.get("http://127.0.0.1:8000/AdministrarCursos/indexCursosPublicados");
        Thread.sleep(2000); // Espera 1.5 segundos
        
    	WebElement barraBusqueda = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputBusqueda")));
    	barraBusqueda.clear();
    	barraBusqueda.click();
    	barraBusqueda.sendKeys("Curso de Spring Boot");
    	Thread.sleep(900);
    	
    	// Utilizando un selector de atributo en Selenium
    	driver.findElement(By.cssSelector("button[data-id-curso='3']")).click();
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnComentarioE")).click(); //Busca el elemento HTML por su id y le da click.
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.className("cancel-btn")).click(); //Busca el elemento HTML cancelar por su id y le da click.
        WebElement tituloModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tituloModal")));
        tituloModal.click();
    	
	}
}
