package tests;

import exceptions.WrongComponentException;
import model.ComponentTypes;
import model.Storage;
import model.vehicles.BaseVehicle;
import model.vehicles.VehicleFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanina on 4/1/15.
 */
public class VehicleTest {
    private BaseVehicle sandTruck, graniteTruck, cementTruck, waterTruck;

    @Before
    public void setUp() throws Exception {
        VehicleFactory factory = new VehicleFactory();
        sandTruck = factory.createVehicleByType(ComponentTypes.SAND, 10.0);
        graniteTruck = factory.createVehicleByType(ComponentTypes.GRANITE, 10.0);
        cementTruck = factory.createVehicleByType(ComponentTypes.CEMENT, 10.0);
        waterTruck = factory.createVehicleByType(ComponentTypes.WATER, 10.0);

    }

    @Test
    public void testValidateComponentType_Sand_to_Granite() throws Exception {
        graniteTruck.validateComponentType(ComponentTypes.SAND);
    }

    @Test
    public void testValidateComponentType_Granite_to_Sand() throws Exception {
        sandTruck.validateComponentType(ComponentTypes.GRANITE);
    }

    @Test(expected = WrongComponentException.class)
    public void testValidateComponentType_Sand_to_Water() throws Exception {
        waterTruck.validateComponentType(ComponentTypes.SAND);
    }

    @Test(expected = WrongComponentException.class)
    public void testValidateComponentType_Sand_to_Cement() throws Exception {
        cementTruck.validateComponentType(ComponentTypes.SAND);
    }
    @Test(expected = WrongComponentException.class)
    public void testValidateComponentType_Water_to_Sand() throws Exception {
        sandTruck.validateComponentType(ComponentTypes.WATER);
    }
    @Test(expected = WrongComponentException.class)
    public void testValidateComponentType_Water_To_Granite() throws Exception {
        graniteTruck.validateComponentType(ComponentTypes.WATER);
    }

    @Test
    public void testValidateComponentType_Water_to_Water() throws Exception {
        sandTruck.validateComponentType(ComponentTypes.SAND);
    }

    @Test
    public void testDeliverAmount() throws Exception {
        Storage storage = new Storage();
        double initialWaterAmntAtStorage  = storage.getAmntOfWater();
        double amntDelivered = waterTruck.getCapacity();
        waterTruck.deliverAmount(storage);
        assert storage.getAmntOfWater() == initialWaterAmntAtStorage + amntDelivered;

    }

    @Test
    public void testDeliverAmount_OthersRemainSame() throws Exception {
        Storage storage = new Storage();
        double initialGraniteAtStorage  = storage.getAmntOfGranite();
        double initialCementAtStorage = storage.getAmntOfCement();
        double initialSandAmntAtStorage = storage.getAmntOfSand();
        waterTruck.deliverAmount(storage);
        assert initialGraniteAtStorage  == storage.getAmntOfGranite() &&
                initialCementAtStorage == storage.getAmntOfCement() &&
                initialSandAmntAtStorage == storage.getAmntOfSand();

    }
}