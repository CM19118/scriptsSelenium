package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebEditarCurso {
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
    public void editarCurso() throws InterruptedException{
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
        Thread.sleep(2500); // Espera 1.5 segundos
        
        driver.findElement(By.id("btnEditarCurso")).click();
        Thread.sleep(1000);
        WebElement tituloElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputT")));
        // Rellenar el formulario de creación de cursos
        tituloElement.sendKeys("Curso de prueba Testing2 - Selenium 9.5");
        tituloCursoTemp = tituloElement.getText();

        driver.findElement(By.id("fechaInicio")).sendKeys("11-19-2023");
        driver.findElement(By.id("fechaFin")).sendKeys("11-20-2023");

        // Seleccionar modalidad
        WebElement modalidadDropdown = driver.findElement(By.id("modalidad"));
        modalidadDropdown.click();
        WebElement modalidadOption = driver.findElement(By.xpath("//option[text()='Semi-Presencial']"));
        modalidadOption.click();
        // Esperar a que el elemento sea presente en el DOM
        WebElement tutorDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tutores")));
        // Hacer clic en el dropdown para activarlo
        tutorDropdown.click();
        // Esperar a que la opción específica sea visible y hacer clic en ella
        WebElement tutorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()='Juan Antonio Perez']")));
        tutorOption.click();
        // Seleccionar categoría
        WebElement categoriaDropdown = driver.findElement(By.id("categoria"));
        categoriaDropdown.click();
        WebElement categoriaOption = driver.findElement(By.xpath("//option[text()='Presentacion']"));
        categoriaOption.click();
        // Rellenar horarios y cupos
        driver.findElement(By.id("horarios")).sendKeys("7:30 A.M hasta las 4:00 P.M de lunes a jueves");
        driver.findElement(By.id("cupos")).sendKeys("12");
        driver.findElement(By.id("descripcionC")).sendKeys("Esta es una deescripcion de curso testing, creada mediante la automatizacion de Selenium");
    	
    }
}
