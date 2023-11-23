package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebCrearNoticia {
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
    public void crearNoticia() throws InterruptedException {
        driver.get("http://127.0.0.1:8000/AdministrarNoticias/indexGestionNoticias");
        Thread.sleep(1000);
        WebElement tituloElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputT")));
        // Rellenar el formulario de creación de cursos
        tituloElement.sendKeys("Noticia creada de prueba, utillizando Selenium.");
        Thread.sleep(900);
        // Seleccionar una imagen desde tu computadora
        WebElement inputArchivo = driver.findElement(By.id("imageInput"));
        // Ruta local del archivo en tu computadora
        //Aca va cambiar su ruta Angela por una imagen suya.
        String rutaLocalImagen = "C:\\Users\\mikes\\Downloads\\logo_minerva.png";
        // Configurar el controlador para detectar archivos locales
        inputArchivo.sendKeys(rutaLocalImagen);
        Thread.sleep(1290);
        WebElement inputDescripcion = driver.findElement(By.className("ck-content"));
        inputDescripcion.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        Thread.sleep(1500);
        WebElement btnCrearNoticia = driver.findElement(By.id("btnCrearNoticias"));
        btnCrearNoticia.click();
        Thread.sleep(1000);
    }
    
}
