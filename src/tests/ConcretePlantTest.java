package tests;

import model.ConcretePlant;
import model.Customer;
import model.Order;
import model.Storage;
import org.junit.Before;
import org.junit.Test;
import util.AltTimeControllerSingleton;

import java.util.Date;

/**
 * Created by yanina on 4/1/15.
 */
public class ConcretePlantTest {
    private ConcretePlant plant;

    @Before
    public void setUp() throws Exception {

        plant = new ConcretePlant();
    }

    @org.junit.Test
    public void testProduceAnotherProduct() throws Exception {
        plant.produceAnotherProduct();

    }

    @Test
    public void assertProductWasAddedToQueue(){
       int initQueueSize = plant.queue.fifo.size();
        plant.produceAnotherProduct();
        assert plant.queue.fifo.size() > initQueueSize;

    }
    @Test
    public void assertNextPartOfTheDayWhenProductProduced(){
        Date init = AltTimeControllerSingleton.getInstance().getCurTimeAsDate();
        plant.produceAnotherProduct();
        assert AltTimeControllerSingleton.getInstance().getCurTimeAsDate().after(init);

    }
    @org.junit.Test
    public void testPlaceOrder() throws Exception {
       int initialNOfOrers = plant.getExistingOrders().size();
        plant.placeOrder(new Order(9, new Customer("Sherry")));
        assert plant.getExistingOrders().size() > initialNOfOrers;

    }

    @Test
    public void testEnsureComponentAvailability_insufficient_items() throws Exception {
        Storage testStorage = plant.getStorage();
        double initSand = testStorage.getAmntOfSand();
        double initGranite = testStorage.getAmntOfGranite();
        double initCement = testStorage.getAmntOfCement();
        double initWater = testStorage.getAmntOfWater();
        plant.ensureComponentAvailability(initSand+2, initGranite+2, initCement+2, initWater+2);

        assert testStorage.getAmntOfSand()> initSand;
        assert testStorage.getAmntOfGranite() > initGranite;
        assert testStorage.getAmntOfCement() > initCement;
        assert testStorage.getAmntOfWater() > initWater;



    }

    @Test
    public void testEnsureComponentAvailability_nothing_happens_when_enough_component() throws Exception {
        Storage testStorage = plant.getStorage();
        double initSand = testStorage.getAmntOfSand();
        double initGranite = testStorage.getAmntOfGranite();
        double initCement = testStorage.getAmntOfCement();
        double initWater = testStorage.getAmntOfWater();
        plant.ensureComponentAvailability(initSand, initGranite, initCement, initWater);

        assert testStorage.getAmntOfSand()== initSand;
        assert testStorage.getAmntOfGranite() == initGranite;
        assert testStorage.getAmntOfCement() == initCement;
        assert testStorage.getAmntOfWater() == initWater;


    }
}