import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;  //импорт не целого класса,а одного конкретного метода статическ


public class SignUpTest {

    WebDriver driver;

    @BeforeMethod     //эта аннотация автоматически запустит наши две строчки кода перед каждым методом(тестом
    public  void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
         driver = new ChromeDriver();
    }

    @Test
    public void zipCode1() {
        /*
        1: Открыть браузер
        2: Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3: Введите 5 цифр, например 12345
        4: Нажать Continue
        5: Проверить, что кнопка Register видна
        6: Закрыть браузер
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isDisplayed);

    }

    @Test
    public void zipCode2() {
        /*
        1: Открыть браузер
        2: Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3: Ввести 4 цифры, например 1234
        4: Нажать Continue
        5: Проверить, что браузер возращает на страницу ввода Zip Code
        6: Закрыть браузер
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed= driver.findElement(By.name("zip_code")).isDisplayed();
        assertTrue(isDisplayed);

    }

    @Test
    public void zipCodeShouldNotBeAcceptedWith6Digits() {
        /*
        1: Открыть браузер
        2: Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3: Ввести 6 цифры, например 123456
        4: Нажать Continue
        5: Проверить, что браузер возращает на страницу ввода Zip Code
        6: Закрыть браузер
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed= driver.findElement(By.name("zip_code")).isDisplayed();
        assertTrue(isDisplayed,"Error message is not displayed");//всегда писать сообщение об ошибке

    }

    @Test
    public void zipCode4() {
        /*
         1: Открыть браузер
        2: Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        3: В поле "First Name" ввести Alesia
        4: В поле "Last Name" ввести Saifullina
        5: В поле "Email" ввести aaa@aaa.aa
        6: В поле "Password" ввести 123456789
        7: Нажать кнопку "Register"
        8: Проверить что надпись "Account is created" видна
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Alesia");
        driver.findElement(By.name("last_name")).sendKeys("Saifullina");
        driver.findElement(By.name("email")).sendKeys("aaa@aaa.aa");
        driver.findElement(By.name("password1")).sendKeys("123456789");
        driver.findElement(By.name("password2")).sendKeys("123456789");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isDisplayed= driver.findElement(By.className("confirmation_message")).isDisplayed();
        assertTrue(isDisplayed);

    }

    @AfterMethod(alwaysRun = true)  //alwaysRun-всегда запускается(при любом рез-те теста)
    public void tearDown(){
        driver.quit();
    }
}
