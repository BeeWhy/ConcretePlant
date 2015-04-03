package model;

import exceptions.EmptyOrderException;
import exceptions.OrderDeliveryExcepiton;

import java.util.ArrayList;

public interface ICustomer {
	/**
	 * @author yanina
	 * Makes order of concrete to the plant.
	 * @param plant specifies which plant should be completing the order
	 * @param amnt specifies how many loadtrucks of concrete are ordered
	 * @throws EmptyOrderException if less than 1 loadtruck is requested the order will be denied.
	 */
	void makeOrder(ConcretePlant plant, int amnt) throws EmptyOrderException;

	/**
	 * @author yanina
	 * The customer is notified by the plant when the order is complete when the plant calls this method.
	 *
	 * @param order
	 * @throws OrderDeliveryExcepiton if something was wrong with the order, such as number of delivered trucks doesn't match the number of requested  trucks
	 */
	void acceptOrder(Order order) throws OrderDeliveryExcepiton;
	/**
	 * @author yanina
	 * Provides list of orders for particular customer
	 * @return list of Orders
	 * @see Order
	 */
	ArrayList<Order> getCustomerOrders();

}
