import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;

public class Driver {

    public void driverDoActions() throws AWTException, InterruptedException {
        UZPageSteps onWorkingUZPage = new UZPageSteps();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        String goFrom = new String("Киев");
        String goTo = new String("Кривой Рог-Главный");
        String tripDate = new String("12.08.2016");



        boolean result = false;
        String orderURL = new String("http://booking.uz.gov.ua/ru/");
        String smsSiteURL = new String("http://yosufovych.sms.ru/");
        String phoneNumber = new String("380981082307");
        String password = new String("31415926535");

        onWorkingUZPage.goToOrderPage(orderURL);
        result = onWorkingUZPage.searchTrain(goFrom, goTo, tripDate);
        if (true == result) {
            //onWorkingUZPage.sendSmsFromSite(smsSiteURL, phoneNumber, password, dateFormat.format(date));
            onWorkingUZPage.httpRequestSMS(dateFormat.format(date), goFrom, goTo, tripDate, phoneNumber);
            System.out.println("Message was send to +" + phoneNumber);
            onWorkingUZPage.exit();
        } else {
            onWorkingUZPage.exit();
            System.out.println("No tickets at " + dateFormat.format(date));
        }
    }
}
