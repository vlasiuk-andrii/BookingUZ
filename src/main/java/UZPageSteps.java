import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UZPageSteps {
    public UZPageSteps() throws AWTException {
    }
    WebDriver driver = new FirefoxDriver();
    Robot robot = new Robot();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(css = "input[name=station_from]")
    private WebElement inputStationFrom;

    @FindBy(css = "div[id=stations_from][style*=visible]")
    private WebElement inputStationFromPopUp;

    @FindBy(css = "input[name=station_till]")
    private WebElement inputStationTill;

    @FindBy(css = "input[name=station_till][style*=visible]")
    private WebElement inputStationTillPopUp;

    @FindBy(id = "date_dep")
    private WebElement dateField;

    @FindBy(className = "main-news")
    private WebElement mainPanel;

    @FindBy(css = "button[name=search]")
    private WebElement searchButton;

    @FindBy(css = "div[id=loading_img][style*=visible]")
    private WebElement waitingIcon;

    @FindBy(id = "ts_res_not_found")
    private WebElement resultNotFound;

    @FindBy(xpath = "//tr/td/div[@title='Плацкарт']")
    private WebElement plackartDiv;

    public void exit(){
        driver.close();
    }

    public void goToOrderPage(String OrderURL){
        driver.get(OrderURL);
    }

    public boolean searchTrain(String from, String till, String date) throws InterruptedException {
        boolean localResult = false;
        // input station_from
        wait.until(ExpectedConditions.visibilityOf(inputStationFrom));
        inputStationFrom.sendKeys(from);
        wait.until(ExpectedConditions.visibilityOf(inputStationFromPopUp));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // input station_till
        inputStationTill.sendKeys(till);
        wait.until(ExpectedConditions.visibilityOf(inputStationTillPopUp));
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // input date
        dateField.clear();
        dateField.sendKeys(date);
        mainPanel.click();

        // click button and wait loading
        searchButton.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=loading_img][style*=visible]")));
        ArrayList<WebElement> webList = null;
        webList.add(waitingIcon);
        wait.until(ExpectedConditions.invisibilityOfAllElements(webList));

        // looking at the result
        try {
            wait.until(ExpectedConditions.visibilityOf(resultNotFound));
        } catch (TimeoutException e){
            try {
                wait.until(ExpectedConditions.visibilityOf(plackartDiv));
                localResult = true;
            } catch (TimeoutException e1){}
        }
        return localResult;

    }

//    public void sendSmsFromSite(String smsSiteURL, String phoneNumber, String password, String messageText){
//        driver.get(smsSiteURL);
//
//        driver.findElement(By.cssSelector("input[name=user_phone]")).sendKeys(phoneNumber);
//        driver.findElement(By.cssSelector("input[name=user_password]")).sendKeys(password);
//        driver.findElement(By.xpath("//span[contains(text(),'Войти')]")).click();
//
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href='/?panel=login&logout=1']")));
//        driver.findElement(By.xpath("//a[@href='?panel=mass&action=redirect']")).click();
//
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("mass_to")));
//        driver.findElement(By.id("mass_to")).sendKeys(phoneNumber);
//        driver.findElement(By.id("mass_text")).sendKeys("На момент "+messageText+" есть кое-какие билеты в продаже");
//        driver.findElement(By.id("mass_sendnow")).click();
//        driver.findElement(By.xpath("//a/span[contains(text(),'Отправить сообщение')]")).click();
//
//    }

    public void httpRequestSMS(String date, String goFrom, String goTo, String tripDate, String phoneNumber) throws InterruptedException {
        driver.get("http://sms.ru/sms/send?api_id=2B608AC1-F08A-A79C-061A-F0DF1D5E6F7A&to="+phoneNumber+"&text="+date+"+available+"+goFrom+"+"+goTo+"+"+tripDate);
        Thread.sleep(5000);

    }

}
