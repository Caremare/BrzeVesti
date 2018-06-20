
package category;

import framework.Helper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestCategories {
    
//    public TestCategories() {
//    }
    
    public static WebDriver driver; // kreiramo atribut tipa WebDriver
                                    // statis smo dodali tek nakom sto smo napravili new ChromeDriver
    public static WebDriverWait wait;
    public static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    
    @BeforeClass 
    public static void setUpClass() {
        
        //dateFormat = new SimpleDateFormat("HH:mm:ss"); //inicijalizovali smo dateFormat
        System.out.println("@BeforeClass: " + dateFormat.format(new Date()));
        
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
                
        //create new WebDriver and WebDriver wait
        //        WebDriver driver = new ChromeDriver();
        //        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        
        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
        
        // Fields on Login page found by name locator and explicit wait
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("qa@cubes.rs");
        
        //WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        //wait.until(ExpectedConditions.visibilityOf(passwordField));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys("cubesqa");
        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        loginButton.click();
        
        System.out.println("Page title is: " + driver.getTitle());
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("@AfterClass: " + dateFormat.format(new Date()));
        driver.quit();
    }
    
    @Before
    public void setUp() {
        
        System.out.println("@Before: " + dateFormat.format(new Date()));
        
        WebElement categories = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Categories")));
        categories.click();
    }
    
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        
        System.out.println("@After: " + dateFormat.format(new Date()));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testCreateNewCategory() {
         
        for (int i = 0; i<5; i++) {        
         
        WebElement addCategoryButton = driver.findElement(By.className("btn"));
        addCategoryButton.click();
        
        WebElement titleField = driver.findElement(By.id("title"));
        titleField.sendKeys(Helper.getRandomText());
        
        WebElement saveButton = driver.findElement(By.id("save-category-button"));
        saveButton.click();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/admin/categories";
        String actualUrl = driver.getCurrentUrl();
        
        Assert.assertEquals("", expectedUrl, actualUrl);
        
        String expectedTitle = "Brze vesti admin  | Categories ".replaceAll("\\s+", " ").trim();
         System.out.println("Expected title: " + expectedTitle);
        String actualTitle = driver.getTitle();
         System.out.println("Actual title: " + actualTitle);
        Assert.assertEquals("Title does not match!", expectedTitle, actualTitle);
        }
        
     }
     
    @Test
    public void testEditLastCategory() {
        
        //WebElement tbody = driver.findElement(By.className("ui-sortable"));  //ovo je isto kao i dole u sledecem redu
        WebElement tbody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        
        System.out.println("Number of rows: " + rows.size());
        
        
        WebElement lastRow = rows.get(rows.size() - 1);
        
        //WebElement editButton = lastRow.findElement(By.className("btn-default"));
        WebElement editButton = lastRow.findElement(By.cssSelector("button[title='Edit']"));
        
        editButton.click();
        
        WebElement titleField = driver.findElement(By.id("title"));
        titleField.clear();
        titleField.sendKeys(Helper.getRandomText());
        
        WebElement saveButton = driver.findElement(By.id("save-category-button"));
        saveButton.click();
        
        // Compare expected and actual
        String expectedUrl = "http://bvtest.school.cubes.rs/admin/categories";
        String actualUrl = driver.getCurrentUrl();
        
        Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);
        
        String expectedTitle = "Brze vesti admin  | Categories ".replaceAll("\\s+", " ").trim();
         System.out.println("Expected title: " + expectedTitle);
        String actualTitle = driver.getTitle();
         System.out.println("Actual title: " + actualTitle);
        Assert.assertEquals("Title does not match!", expectedTitle, actualTitle);
    }
     
    @Test
    public void testDeleteFirstCategory () {
        //WebElement tbody = driver.findElement(By.className("ui-sortable"));  //ovo je isto kao i dole u sledecem redu
        WebElement tbody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        
        System.out.println("Number of rows: " + rows.size());
        
        
        WebElement firstRow = rows.get(0);
        
        WebElement deleteButton = firstRow.findElement(By.cssSelector("button[title='Delete']"));
    }
}
