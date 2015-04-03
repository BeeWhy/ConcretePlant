package util;

import model.IEvent;
import model.product.ConcreteProduct;

import java.util.ArrayList;
import java.util.List;

public class TimeControllerSingleton {
    public enum PartOfDay {
        MORNING, AFTERNOON, EVENING, NIGHT
    }

    public int dayCounter = 1;
    private static TimeControllerSingleton instance;
    private final List<PartOfDay> timestamps = new ArrayList<PartOfDay>(4);
    private String curTime;
    public int iterator = 0;

    private TimeControllerSingleton() {
        // no one can create this guy, and only one exists
        timestamps.add(PartOfDay.MORNING);
        timestamps.add(PartOfDay.AFTERNOON);
        timestamps.add(PartOfDay.EVENING);
        timestamps.add(PartOfDay.NIGHT);
        curTime = "Day " + dayCounter + " " +timestamps.get(0); // we start in the morning
        System.out.println("Current time is " + curTime);
    }

    public static TimeControllerSingleton getInstance() {
        if (instance == null) {
            instance = new TimeControllerSingleton();
        }
        return instance;
    }

    public String getCurrentTime() {
        return curTime;
    }

    public void setTimeStampOnEvent(IEvent event) {
        if (event instanceof ConcreteProduct) {
            // when we produced a new concrete loadtruck we move to the next
            // part
            // of the day
            if (iterator < timestamps.size() - 1) {
                iterator++;
            } else { // if it was night now should be morning
                dayCounter++;
                iterator = 0;
                System.out.println("=================NEXT DAY===================");
            }
            curTime = " Day " + dayCounter + " " + timestamps.get(iterator);
            System.out.println("Current time is " + curTime);

        }
        event.setTimeStamp(curTime);
    }

}
