package tests;

import model.Customer;
import model.Order;
import model.product.ConcreteProduct;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanina on 4/1/15.
 */
public class OrderTest {
    private Order order;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Francis");
        order = new Order(3, customer);

    }

    @Test
    public void testAcceptDelivery() throws Exception {
        int deliveredItems = order.getDeliveredItemsCount();
        order.acceptDelivery(new ConcreteProduct());
        assert order.getDeliveredItemsCount() == deliveredItems+1;

    }

    @Test
    public void testGetDeliveredItemsCount() throws Exception {
        order.acceptDelivery(new ConcreteProduct());
        assert order.getDeliveredItemsCount() == order.getNumberOfProducts() - order.getNumberRemaining();
    }

    @Test
    public void testGetNumberRemaining() throws Exception {
        order.acceptDelivery(new ConcreteProduct());
        assert order.getNumberRemaining() == order.getNumberOfProducts() - order.getDeliveredItemsCount();
    }
}