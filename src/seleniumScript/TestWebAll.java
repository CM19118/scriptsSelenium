package seleniumScript;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebAll {

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
    public void crearCurso() throws InterruptedException {
        // Navegar a la página de creación de cursos
        driver.get("http://127.0.0.1:8000/AdministrarCursos/indexCrearCurso");
        // Esperar a que el elemento "titulo" esté presente en la página
        WebElement tituloElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("titulo")));
        // Rellenar el formulario de creación de cursos
        tituloElement.sendKeys("Curso de prueba Testing2 - Selenium 9.0");
        tituloCursoTemp = tituloElement.getText();
        // Seleccionar una imagen desde tu computadora
        WebElement inputArchivo = driver.findElement(By.id("archivo-cargado"));
        
        // Ruta local del archivo en tu computadora
        String rutaLocalImagen = "C:\\Users\\mikes\\Downloads\\logo_minerva.png";

        // Configurar el controlador para detectar archivos locales
        inputArchivo.sendKeys(rutaLocalImagen);
        
        // Seleccionar fecha de inicio y finalización
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
        driver.findElement(By.id("horarios")).sendKeys("7:00 A.M hasta las 4:00 P.M de lunes a jueves");
        driver.findElement(By.id("cupos")).sendKeys("22");

        driver.findElement(By.id("descripcionC")).sendKeys("Esta es una deescripcion de curso testing, creada mediante la automatizacion de Selenium");
        // Rellenar el resto de los campos según la estructura de tu formulario

        // Hacer clic en el botón para agregar contenido
        driver.findElement(By.id("btn-AgregarContenido")).click();

        
        // Esperar a que el elemento sea presente en el DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalAggContenido")));
        // Rellenar el formulario de contenido en el modal
        WebElement tituloModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tituloModal")));
        tituloModal.click();
        tituloModal.sendKeys("Titulo 1 - testing y automatizacion con selenium");
        
        driver.findElement(By.id("descripcionModal")).sendKeys("Este es un contenido de prueba y testeo, mediante el testing de selenium3.");

        // Hacer clic en el botón de crear en el modal
        driver.findElement(By.id("btn-Crear")).click();
        
        Thread.sleep(3500); // Espera 3.5 segundos

        driver.findElement(By.id("btnCrearCurso")).click();
        // Puedes agregar aserciones aquí para verificar si el curso se creó correctamente
        
        Thread.sleep(700);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("swal2-confirm"))).click();;
    }
        
    @Test(priority = 2)
    public void verCursoDisponible() throws InterruptedException{
    	driver.get("http://127.0.0.1:8000/AdministrarCursos/indexCursosPublicados");
        Thread.sleep(2000); // Espera 1.5 segundos
        
    	WebElement barraBusqueda = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputBusqueda")));
    	barraBusqueda.clear();
    	barraBusqueda.click();
    	barraBusqueda.sendKeys("Curso de Spring Boot");
    	Thread.sleep(500);
    	
    	// Utilizando un selector de atributo en Selenium
    	driver.findElement(By.cssSelector("button[data-id-curso='3']")).click();
        Thread.sleep(2500); // Espera 1.5 segundos
    	driver.findElement(By.cssSelector("button[data-bs-target='#tema1']")).click();
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.cssSelector("button[data-bs-target='#tema2']")).click();
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnComentarioE")).click(); //Busca el elemento HTML por su id y le da click.
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.className("cancel-btn")).click(); //Busca el elemento HTML cancelar por su id y le da click.
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnVerMasEst")).click(); //
    	Thread.sleep(1000); //Espera 5 milisegundos.
    	driver.findElement(By.cssSelector("button[data-id-estudiante='9']")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.cssSelector("button[data-bs-dismiss='modal']")).click();
    	Thread.sleep(500);
    	driver.findElement(By.id("btnBackMain")).click();
    	Thread.sleep(1200);
    }
    
    @Test(priority = 3)
    public void buscarCursoDisponible() throws InterruptedException{
    	Thread.sleep(800);
    	WebElement barraBusqueda = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputBusqueda")));
    	barraBusqueda.clear();
    	barraBusqueda.click();
    	Thread.sleep(300);
    	barraBusqueda.sendKeys(tituloCursoTemp);
    	Thread.sleep(1000);
    }
    
    @Test(priority = 4)
    public void verCursoDisponible2() throws InterruptedException {
    	// Utilizando un selector de atributo en Selenium
    	driver.findElement(By.cssSelector(".botonCurso.botonFiltroActivoCurso.btnVerMasCurso")).click();
        Thread.sleep(1500); // Espera 1.5 segundos
    	driver.findElement(By.cssSelector("button[data-bs-target='#tema1']")).click();
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnComentarioE")).click(); //Busca el elemento HTML por su id y le da click.
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.className("cancel-btn")).click(); //Busca el elemento HTML cancelar por su id y le da click.
    	Thread.sleep(1000); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnVerMasEst")).click();
    	Thread.sleep(500);
    	driver.findElement(By.id("btnBackMain")).click();
    }
    
    @Test(priority = 5)
    public void deshabilitarCurso() throws InterruptedException{
    	Thread.sleep(1200);
    	WebElement deshabilitarButton = driver.findElement(By.className("btnDeshabilitarCurso"));
    	deshabilitarButton.click();
    	Thread.sleep(700);
    	driver.findElement(By.cssSelector(".swal2-confirm.swal2-styled.swal2-default-outline")).click();
        Thread.sleep(700);
        driver.findElement((By.cssSelector(".swal2-confirm.swal2-styled"))).click();;
        Thread.sleep(1000);
    }
    
    @Test(priority = 6)
    public void verCursoNoDisponible() throws InterruptedException{
    	driver.get("http://127.0.0.1:8000/AdministrarCursos/indexCursosNoDisponibles");
        Thread.sleep(2000); // Espera 1.5 segundos
    	// Utilizando un selector de atributo en Selenium
    	driver.findElement(By.cssSelector(".botonCurso.botonFiltroActivoCurso.btnVerMasCurso")).click();
        Thread.sleep(500); // Espera 1.5 segundos
    	driver.findElement(By.cssSelector("button[data-bs-target='#tema1']")).click();
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnComentarioE")).click(); //Busca el elemento HTML por su id y le da click.
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.className("cancel-btn")).click(); //Busca el elemento HTML cancelar por su id y le da click.
    	Thread.sleep(500); //Espera 5 milisegundos.
    	driver.findElement(By.id("btnVerMasEst")).click(); //
    	Thread.sleep(500);
    	driver.findElement(By.id("btnBackMain")).click();
    	Thread.sleep(1500); 
    }
    
    @Test(priority = 7)
    public void habilitarCurso() throws InterruptedException{
    	WebElement habilitarButton = driver.findElement(By.cssSelector(".botonFiltroDesactivoCurso.btnHabilitarCurso"));
    	habilitarButton.click();
    	Thread.sleep(700);
    	driver.findElement(By.cssSelector(".swal2-confirm.swal2-styled.swal2-default-outline")).click();
        Thread.sleep(700);
        driver.findElement((By.cssSelector(".swal2-confirm.swal2-styled"))).click();;
        Thread.sleep(1000);
    }
}
