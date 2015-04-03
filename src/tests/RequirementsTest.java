package tests;

import exceptions.*;
import model.*;
import model.components.Cement;
import model.components.Granite;
import model.components.Sand;
import model.components.Water;
import model.product.ConcreteProduct;
import model.product.ConcreteProductBuilder;
import model.vehicles.BaseVehicle;
import model.vehicles.SandAndGraniteVehicle;
import org.junit.Before;
import org.junit.Test;
import util.AltTimeControllerSingleton;
import util.TimeControllerSingleton;

import java.util.Date;

/**
 * Created by yanina on 4/1/15.
 */
public class RequirementsTest {
    private ConcretePlant plant;
    private Storage mockStorage;

    @Before
    public void setUp() throws Exception {
        plant = new ConcretePlant();
        mockStorage = new Storage();
        mockStorage.addAmount(100.0, ComponentTypes.SAND);
        mockStorage.addAmount(100.0, ComponentTypes.GRANITE);
        mockStorage.addAmount(100.0, ComponentTypes.CEMENT);
        mockStorage.addAmount(100.0, ComponentTypes.WATER);

    }

    @Test(expected = WrongComponentException.class)
    public final void ifComponentsInWrongOrderThrowException() throws InsufficientSuppliesException, WrongComponentException {
        ConcreteProductBuilder builder = new ConcreteProductBuilder(mockStorage, 1);
        builder.addFourth(new Water(4.0));

    }

    @Test(expected = ComponentMissingException.class)
    public final void ifNotAllComponentsAreAddedExceptionIsThrown() throws InsufficientSuppliesException, WrongComponentException, WrongQuantityOfComponentException, ComponentMissingException {
        ConcreteProductBuilder builder = new ConcreteProductBuilder(mockStorage, 1);
        builder.addFirst(new Sand(0.1));
        builder.addSecond(new Granite(0.2));
        builder.addThird(new Cement(0.3));
        builder.mixUp();

    }

    private void createSomeProductWithAmountOfComponents(double sand, double granite, double cement, double water) throws InsufficientSuppliesException, WrongComponentException, WrongQuantityOfComponentException, ComponentMissingException {
        ConcreteProductBuilder builder = new ConcreteProductBuilder(mockStorage, 1);
        builder.addFirst(new Sand(sand));
        builder.addSecond(new Granite(granite));
        builder.addThird(new Cement(cement));
        builder.addFourth(new Water(water));
        builder.mixUp();
    }

    @Test(expected = WrongComponentException.class)
    public final void testWrongComponentLoadedInTruck() throws WrongComponentException {
        BaseVehicle sandVehicle = new SandAndGraniteVehicle(3.0);
        sandVehicle.acceptRequest(new Storage(), ComponentTypes.WATER);
    }


    @Test
    public final void testAllProportionsAreCorrect() throws WrongQuantityOfComponentException, InsufficientSuppliesException, WrongComponentException, ComponentMissingException {
        createSomeProductWithAmountOfComponents(0.5, 0.25, 0.1, 0.15);

    }

    @Test(expected = WrongQuantityOfComponentException.class)
    public final void testWrongSandProportionsThrowException() throws WrongQuantityOfComponentException, InsufficientSuppliesException, WrongComponentException, ComponentMissingException {
        createSomeProductWithAmountOfComponents(0.6, 0.25, 0.1, 0.15);
    }

    @Test(expected = WrongQuantityOfComponentException.class)
    public final void testWrongGraniteProportionsThrowException() throws WrongQuantityOfComponentException, InsufficientSuppliesException, WrongComponentException, ComponentMissingException {
        createSomeProductWithAmountOfComponents(0.5, 0.26, 0.1, 0.15);

    }

    @Test(expected = WrongQuantityOfComponentException.class)
    public final void testWrongCementProportionsThrowException() throws WrongQuantityOfComponentException, InsufficientSuppliesException, WrongComponentException, ComponentMissingException {
        createSomeProductWithAmountOfComponents(0.5, 0.25, 0.2, 0.15);

    }

    @Test(expected = WrongQuantityOfComponentException.class)
    public final void testWrongWaterProportionsThrowException() throws WrongQuantityOfComponentException, InsufficientSuppliesException, WrongComponentException, ComponentMissingException {
        createSomeProductWithAmountOfComponents(0.5, 0.25, 0.1, 0.13);
    }

    @Test
    public final void testIfSandInGraniteTruckNoExceptionIsThrown() throws WrongComponentException {
        BaseVehicle sandVehicle = new SandAndGraniteVehicle(3.0);
        sandVehicle.acceptRequest(new Storage(), ComponentTypes.SAND);
    }

    @Test
    public final void testIfGraniteInSandTruckNoExceptionIsThrown() throws WrongComponentException {
        BaseVehicle sandVehicle = new SandAndGraniteVehicle(3.0);
        sandVehicle.acceptRequest(new Storage(), ComponentTypes.GRANITE);

    }

    @Test
    public final void testIfProductNotDeliveredOnTimeItExpires() {
        plant.produceAnotherProduct();
        ConcreteProduct firstProduct = plant.queue.fifo.getFirst();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        assert !plant.queue.fifo.contains(firstProduct);
    }

    @Test
    public final void testMax4TrucksProducedPerDay(){
        Date initial  = AltTimeControllerSingleton.getInstance().getCurTimeAsDate();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        assert hoursDifference(AltTimeControllerSingleton.getInstance().getCurTimeAsDate(), initial) >24;

    }

    private int hoursDifference(Date date1, Date date2) {

        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
    }

    @Test
    public final void clientWithOrderOf5GetsOrderWithin24() throws EmptyOrderException {
        ICustomer Alice = new Customer("Alice");
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        Alice.makeOrder(plant, 5);
        Date initial = AltTimeControllerSingleton.getInstance().getCurTimeAsDate();
        plant.produceAnotherProduct();
        plant.produceAnotherProduct();
        assert Alice.getCustomerOrders().get(0).getDeliveredItemsCount() == Alice.getCustomerOrders().get(0).getNumberOfProducts();
        assert AltTimeControllerSingleton.getInstance().getCurTimeAsDate().after(initial);

    }

    @Test
    public final void whenOrdersExistEachNewTruckAppliesToOrderRightAway() throws EmptyOrderException {
        Customer Alice = new Customer("Bob");
        Alice.makeOrder(plant, 5);
        plant.produceAnotherProduct();
        for (ConcreteProduct p : plant.queue.fifo){
           assert p.isSold();
        }

    }



}
