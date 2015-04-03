package tests;

import model.ComponentTypes;
import model.ConcretePlant;
import model.Storage;
import model.components.Cement;
import model.components.Granite;
import model.components.Sand;
import model.components.Water;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanina on 4/1/15.
 */
public class StorageTest {
    ConcretePlant testPlant;
    Storage testStorage;

    @Before
    public void setUp() throws Exception {
        testPlant = new ConcretePlant();
        testStorage = testPlant.getStorage();

    }

    @Test
    public void testRequestMoreAmount() throws Exception {
        double initAmountOfSand = testStorage.getAmntOfSand();
        double initAmountOfGranite = testStorage.getAmntOfGranite();
        double initAmountOfCement = testStorage.getAmntOfCement();
        double initAmountOfWater = testStorage.getAmntOfWater();

        testStorage.requestMoreAmount(ComponentTypes.SAND);
        testStorage.requestMoreAmount(ComponentTypes.GRANITE);
        testStorage.requestMoreAmount(ComponentTypes.CEMENT);
        testStorage.requestMoreAmount(ComponentTypes.WATER);

        assert testStorage.getAmntOfSand() > initAmountOfSand;
        assert testStorage.getAmntOfGranite() > initAmountOfGranite;
        assert testStorage.getAmntOfCement() > initAmountOfCement;
        assert testStorage.getAmntOfWater() > initAmountOfWater;

    }

    @Test
    public void testAddAmount() throws Exception {

        double additionVal = 3.0;

        double initAmountOfSand = testStorage.getAmntOfSand();
        double initAmountOfGranite = testStorage.getAmntOfGranite();
        double initAmountOfCement = testStorage.getAmntOfCement();
        double initAmountOfWater = testStorage.getAmntOfWater();

        testStorage.addAmount(additionVal, ComponentTypes.SAND);
        testStorage.addAmount(additionVal, ComponentTypes.GRANITE);
        testStorage.addAmount(additionVal, ComponentTypes.CEMENT);
        testStorage.addAmount(additionVal, ComponentTypes.WATER);


        assert testStorage.getAmntOfSand() - initAmountOfSand == additionVal;
        assert testStorage.getAmntOfGranite() - initAmountOfGranite  == additionVal;
        assert testStorage.getAmntOfCement() - initAmountOfCement  == additionVal;
        assert testStorage.getAmntOfWater() - initAmountOfWater  == additionVal;

    }


    @Test
    public void testConsume() throws Exception {

        double consumptionVal = 3.0;
        testPlant.ensureComponentAvailability(consumptionVal, consumptionVal, consumptionVal, consumptionVal);

        double initAmountOfSand = testStorage.getAmntOfSand();
        double initAmountOfGranite = testStorage.getAmntOfGranite();
        double initAmountOfCement = testStorage.getAmntOfCement();
        double initAmountOfWater = testStorage.getAmntOfWater();

        testStorage.consume(consumptionVal, new Sand(consumptionVal));
        testStorage.consume(consumptionVal, new Granite(consumptionVal));
        testStorage.consume(consumptionVal, new Cement(consumptionVal));
        testStorage.consume(consumptionVal, new Water(consumptionVal));


        assert testStorage.getAmntOfSand() - initAmountOfSand == -consumptionVal;
        assert testStorage.getAmntOfGranite() - initAmountOfGranite  == -consumptionVal;
        assert testStorage.getAmntOfCement() - initAmountOfCement  == -consumptionVal;
        assert testStorage.getAmntOfWater() - initAmountOfWater  == -consumptionVal;


    }
}