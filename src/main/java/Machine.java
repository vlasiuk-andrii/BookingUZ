import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Machine {
    public static void main(String[] args) throws AWTException, InterruptedException {

        UZPageSteps onWorkingUZPage = new UZPageSteps();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String goFrom = new String("Киев");
        String goTo = new String("Кривой Рог-Главный");
        String tripDate = new String("01.07.2016");

        String loginPageURL = new String("http://booking.uz.gov.ua/ru/authorization/");
        String orderURL = new String("http://booking.uz.gov.ua/ru/");
        String smsSiteURL = new String("http://yosufovych.sms.ru/");
        boolean result = false;
        String phoneNumber  = new String("+380981082307");
        String password = new String("31415926535");

        //onWorkingUZPage.goToLoginPage(loginPageURL);
        //onWorkingUZPage.loginAsUser("humanxb@mail.ru", "31415926535pi");
        onWorkingUZPage.goToOrderPage(orderURL);
        result = onWorkingUZPage.searchTrain(goFrom, goTo, tripDate);
        if (true == result) {
            //onWorkingUZPage.sendMessageToPhone(smsSiteURL, phoneNumber, password, dateFormat.format(date));
            //onWorkingUZPage.httpRequestSMS(dateFormat.format(date));
            System.out.println("wait a bit for sms");
            onWorkingUZPage.exit();
        } else {
            onWorkingUZPage.exit();
            System.out.println("just exit");
        }

    }
}
