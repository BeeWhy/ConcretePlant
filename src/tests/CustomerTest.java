package tests;

import exceptions.OrderDeliveryExcepiton;
import model.ConcretePlant;
import model.Customer;
import model.ICustomer;
import model.Order;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanina on 4/1/15.
 */
public class CustomerTest {
    private ICustomer customer;
    private ConcretePlant plant;

    @Before
    public void setUp() throws Exception {

        customer = new Customer("Susan");
        plant = new ConcretePlant();
    }

    @Test
    public void testMakeOrder() throws Exception {
        int nOfOrders = plant.getExistingOrders().size();

        customer.makeOrder(plant, 2);
        assert plant.getExistingOrders().size() == nOfOrders+1;
    }

    @Test(expected = OrderDeliveryExcepiton.class)
    public void testAcceptOrder_wrongAmout() throws Exception {
        customer.makeOrder(plant, 2);
        customer.acceptOrder(new Order(1, customer));

    }

    @Test(expected = OrderDeliveryExcepiton.class)
    public void testAcceptOrder_wrongCustomer() throws Exception {
        customer.makeOrder(plant, 2);
        customer.acceptOrder(new Order(1, new Customer("Justin")));

    }
}