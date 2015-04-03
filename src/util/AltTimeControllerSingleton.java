package util;

import model.IEvent;
import model.product.ConcreteProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yanina on 4/1/15.
 * Singleton implementation of the current time on the plant
 */
public class AltTimeControllerSingleton {
    SimpleDateFormat format;
    Date curTime;
    Calendar cal;

    private static AltTimeControllerSingleton instance;

    private AltTimeControllerSingleton() {
        format = new SimpleDateFormat("d HH:mm");
        try {
            curTime = format.parse("1 00:00");
            cal = Calendar.getInstance();
            cal.setTime(curTime);
            System.out.println("Current time is " + curTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author yanina
     * Provides instance of the time singleton that gices access to current time and sets stamps on events
     * @return
     */
    public static AltTimeControllerSingleton getInstance() {
        if (instance == null) {
            instance = new AltTimeControllerSingleton();
        }
        return instance;
    }

    /**
     * @author yanina
     * Provides Date object of the current time
     * @return
     */
    public Date getCurTimeAsDate() {
        return curTime;
    }

    /**
     * @author yanina
     * Provides current time on the plant as string
     *
     * @return
     */
    public String getCurrentTimeString() {
        return format.format(curTime);
    }

    /**
     * @author yanina
     * Sets a time stamp on an item that occurs with consideration of time, ie order is made, product is created
     *
     * @param event
     */
    public void setTimeStampOnEvent(IEvent event) {
        if (event instanceof ConcreteProduct) {

            cal.add(Calendar.HOUR, 6);
            curTime = cal.getTime();
            System.out.println("Current time is Day " + curTime);

        }
        event.setTimeStamp("Day " + getCurrentTimeString());
    }


}
