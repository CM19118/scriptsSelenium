package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestWebLogin {
	 // Selenium contiene una clase para trabajar con Chrome, añadimos nombre al objeto.
    // Chrome Driver contiene el código para interactuar con Chrome
    ChromeDriver driver;
    String url = "http://127.0.0.1:8000/login";

    // Método para invocar al navegador Chrome
    @BeforeClass
    public void invocarNavegador() {
        // Ruta donde se encuentra el ejecutable de ChromeDriver
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\mikes\\OneDrive\\Documentos\\TestDSI\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver(); // Inicializando el objeto
        driver.manage().window().maximize(); // Maximizando la ventana
        driver.get(url);
    }

    // Segundo caso de prueba para verificar el login
    @Test(priority = 0)
    public void verificarLoginDeGuru99Application() {
        // Para identificar elementos como el id, Selenium provee una interfaz llamada WebElement
        WebElement userId = driver.findElement(By.name("email")); // Locator
        WebElement userPassword = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));

        // Ejecución con el método sendKeys
        userId.sendKeys("cm18064@ues.edu.sv");
        userPassword.sendKeys("Minerva.23");
        loginButton.click();
    }

    // Para cerrar el navegador después de todas las pruebas
    @AfterClass
    public void closeBrowser() {
        // Quit cierra todas las pestañas y close cierra únicamente la pestaña activa
        //driver.quit();
    }
}
