package model;

import java.util.LinkedList;
import java.util.List;

import exceptions.ComponentMissingException;
import exceptions.InsufficientSuppliesException;
import exceptions.OrderDeliveryExcepiton;
import exceptions.SoldProductException;
import exceptions.WrongComponentException;
import exceptions.WrongQuantityOfComponentException;
import model.components.Cement;
import model.components.Granite;
import model.components.Sand;
import model.components.Water;
import model.product.ConcreteProduct;
import model.product.ConcreteProductBuilder;
import model.product.ProductQueue;
import util.AltTimeControllerSingleton;

/**
 * The engine of the project, the plant communicates with the other actors such as Storage, Customers, Product Builder that mixes up concreate
 * and ensures that all requirements and guildelines of the plant are met.
 */
public class ConcretePlant {
    /**
     * List of existing orders that have not yet been fulfilled
     */
    private final LinkedList<Order> existingOrders;
    /**
     * order that is currently delivered
     */
    private Order currentOrder;
    /**
     * The plant always knows what time it is, and to put time stamps on orders and products
     */
    private final AltTimeControllerSingleton  instance;
    /**
     * The plant has a storage where all the components are located
     */
    private final Storage storage;
    /**
     * The plant uses a queue to control expiration of the products
     * MAX 4 available product in storage
     */
    public final ProductQueue queue;

    /**
     * This counter is used to generate ids of the product
     */
    private int productIdCounter = 1;

    /**
     * Constructs the plant with initialization of its core components such as existing orders, time controller instance,
     * storage and queue of non-expired products
     */
    public ConcretePlant() {
        super();
        this.existingOrders = new LinkedList<Order>();
        this.instance = AltTimeControllerSingleton.getInstance();
        this.storage = new Storage();
        this.queue = new ProductQueue();
    }

    /**
     * Provides the list of existing un-fulfilled orders
     * @return List of existing orders
     */
    public LinkedList<Order> getExistingOrders() {
        return existingOrders;
    }

    /**
     * provides instance of the storage of the plant
     * @return Storage of the plant
     */
    public Storage getStorage() {
        return storage;
    }

    /**Ensures that there is sufficient amount of each product available at the storage to create a new product.
     * If the amount is not sufficient, it requests for more of this component to be delivered.
     * @see Storage
     * This method is called before prodution of each one concrete product (ie one loadtruck of product)
     * @param sand
     * @param granite
     * @param cement
     * @param water
     * @throws WrongComponentException if we are requesting wrong component of the delivery vehicle
     */
    public void ensureComponentAvailability(double sand, double granite,
                                            double cement, double water) throws WrongComponentException {
        while (storage.amntOfSand < sand) {
            storage.requestMoreAmount(ComponentTypes.SAND);
        }
        while (storage.amntOfGranite < granite) {
            storage.requestMoreAmount(ComponentTypes.GRANITE);
        }
        while (storage.amntOfCement < cement) {
            storage.requestMoreAmount(ComponentTypes.CEMENT);
        }
        while (storage.amntOfWater < water) {
            storage.requestMoreAmount(ComponentTypes.WATER);
        }
    }


    /**
     * Produces one product equal to one loadtruck.
     * In result the new product is added to the queue of the products, and in case there are orders in place, it is immediately delivered to the customer
     * @see ProductQueue
     */
    public void produceAnotherProduct() {
        try {
            double[] componentAmounts = {5.0, 2.5, 1.0, 1.5};
            ensureComponentAvailability(componentAmounts[0],
                    componentAmounts[1], componentAmounts[2],
                    componentAmounts[3]);
            ConcreteProductBuilder builder = new ConcreteProductBuilder(
                    storage, productIdCounter++);
            ConcreteProduct product = builder
                    .addFirst(new Sand(componentAmounts[0]))
                    .addSecond(new Granite(componentAmounts[1]))
                    .addThird(new Cement(componentAmounts[2]))
                    .addFourth(new Water(componentAmounts[3])).mixUp();
            queue.put(product);

            checkExistingOrders();

        } catch (WrongComponentException e) {
            System.out
                    .println("learn your elements and try again next time, loser!");
            e.printStackTrace();
        } catch (InsufficientSuppliesException e) {
            System.out
                    .println("I thought you checked if components were sufficient");
            e.printStackTrace();
        } catch (WrongQuantityOfComponentException e) {
            System.out
                    .println("learn your proportions before you break the system, dumass!");
            e.printStackTrace();
        } catch (ComponentMissingException e) {
            System.out.println("You don't think you forgot something?");
            e.printStackTrace();
        }

    }

    /**
     * Checks if there are any available orders in place, to whom we can deliver the product
     * every time a product is created we are checking if any customers are waiting for it to be delivered
     */
    private void checkExistingOrders() {

        if (currentOrder == null && !existingOrders.isEmpty()) {
            currentOrder = existingOrders.removeFirst();
        }
        if (currentOrder != null) {
            completeCurrentOrderAsMuchAsCan();// a new order might have been
            // placed while we were making
            // the product
        }
    }

    /**
     * Ensures all available unsold products will be sold toward current order
     */
    private void completeCurrentOrderAsMuchAsCan() {

        int remaining = currentOrder.getNumberRemaining();
        for (int i = 0; i < remaining; i++) {

            // iterate the queue until next unsold item
            // if found - add to order
            // break inner loop, continue outer loop
            // while (queue.fifo.iterator().hasNext()){
            for (int j = 0; j < queue.fifo.size(); j++) {
                ConcreteProduct candidate = queue.fifo.get(j);
                if (!candidate.isSold()) {
                    try {
                        candidate.sell();
                        currentOrder.acceptDelivery(candidate);
                        break;
                    } catch (SoldProductException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        if (currentOrder.getNumberRemaining() == 0) {
            completeOrder();
        }

    }

    /**
     * When all the  loadtrucks with concrete product have been delivered to the customer, the customer is notified about delivery completion
     * @see ICustomer, Customer
     */
    private void completeOrder() {
        try {
            ICustomer c = currentOrder.getCustomer();
            c.acceptOrder(currentOrder);
        } catch (OrderDeliveryExcepiton e) {
            System.out
                    .println("Dude, something went wrong with delivering this order!");
            e.printStackTrace();
        }
        currentOrder = null;

    }

    /**
     * Allows the customer to place a new order to the plant
     * @param order In order are contained specifics such as amount of the order which can never be 0.
     *
     */
    public void placeOrder(Order order) {
        System.out.println("The plant accepted the order");
        instance.setTimeStampOnEvent(order);
        existingOrders.add(order);
    }

}
