import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException, AWTException {

        Driver driver = new Driver();

        int i = 0;
        do {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            i++;
            System.out.println("i = " + i + "   " + dateFormat.format(date));
            driver.driverDoActions();
            Thread.sleep(10*60000);
        } while (true);
    }
}
