import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UZPageSteps {
    public UZPageSteps() throws AWTException {
    }

    WebDriver driver = new FirefoxDriver();
    Robot robot = new Robot();
    WebDriverWait wait = new WebDriverWait(driver, 5);

    public void goToLoginPage(String URL){
        driver.get(URL);
    }

    public void exit(){
        driver.close();
        //System.exit(0);
    }

    public void loginAsUser(String userName, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=login]")));
        driver.findElement(By.cssSelector("input[name=login]")).sendKeys(userName);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        driver.findElement(By.cssSelector("input[name=passwd]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[class=button]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Выйти')]")));
    }

    public void goToOrderPage(String OrderURL){
        driver.get(OrderURL);
    }

    public boolean searchTrain(String from, String till, String date) throws InterruptedException {
        boolean localResult = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=station_from]")));
        driver.findElement(By.cssSelector("input[name=station_from]")).sendKeys(from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=stations_from][style*=visible]")));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        driver.findElement(By.cssSelector("input[name=station_till]")).sendKeys(till);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=stations_till][style*=visible]")));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        driver.findElement(By.id("date_dep")).clear();
        driver.findElement(By.id("date_dep")).sendKeys(date);
        driver.findElement(By.className("main-news")).click();

        driver.findElement(By.cssSelector("button[name=search]")).click();
        Thread.sleep(6000);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ts_res_not_found")));
        } catch (TimeoutException e){
            driver.findElement(By.id("ts_res_tbl"));
            localResult = true;
        }
        return localResult;

    }

    public void sendMessageToPhone(String smsSiteURL, String phoneNumber, String password, String messageText){
        driver.get(smsSiteURL);

        driver.findElement(By.cssSelector("input[name=user_phone]")).sendKeys(phoneNumber);
        driver.findElement(By.cssSelector("input[name=user_password]")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(text(),'Войти')]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href='/?panel=login&logout=1']")));
        driver.findElement(By.xpath("//a[@href='?panel=mass&action=redirect']")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("mass_to")));
        driver.findElement(By.id("mass_to")).sendKeys(phoneNumber);
        driver.findElement(By.id("mass_text")).sendKeys("На момент "+messageText+" есть кое-какие билеты в продаже");
        driver.findElement(By.id("mass_sendnow")).click();
        driver.findElement(By.xpath("//a/span[contains(text(),'Отправить сообщение')]")).click();

    }

    public void httpRequestSMS(String date){
        driver.get("http://sms.ru/sms/send?api_id=2B608AC1-F08A-A79C-061A-F0DF1D5E6F7A&to=380981082307&text="+"На+момент+"+date+"+есть+кое-какие+билеты+в+продаже");
    }

}
