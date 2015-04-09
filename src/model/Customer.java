package model;

import util.AltTimeControllerSingleton;
import exceptions.EmptyOrderException;
import exceptions.OrderDeliveryExcepiton;

import java.util.ArrayList;

public class Customer implements ICustomer {
	private String name;
	private ArrayList<Order> customerOrders;

	/**
	 * @author yanina
	 * Default constructor of the customer
	 * Initializes the list of customer's orders
	 * @param name name of the customer
	 */
	public Customer(String name) {
		super();
		this.name = name;
		customerOrders = new ArrayList<Order>();
	}


	/**
	 * @author yanina
	 * by amount for product in entire project we mean loadtrucks as this is how much expires periodically
	 * @param plant specifies which plant should be completing the order
	 * @param amnt specifies how many loadtrucks of concrete are ordered
	 * @throws EmptyOrderException
	 */
	public void makeOrder(ConcretePlant plant, int amnt)
			throws EmptyOrderException {

		System.out.println("Customer " + name + " placed an order for " + amnt + " loadtrucks.");
		Order order = new Order(amnt, this);
		customerOrders.add(order);
		plant.placeOrder(order);
	}

	/**
	 * @author yanina
	 * Provides list of orders for particular customer
	 * @return list of Orders
	 * @see Order
	 */
	public ArrayList<Order> getCustomerOrders() {
		return customerOrders;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 *@author yanina
	 * To avoid risk of fraudulent notification customer checks that params of complete order match the original order
	 * @param order
	 * @throws OrderDeliveryExcepiton
	 */
	@Override
	public void acceptOrder(Order order) throws OrderDeliveryExcepiton {
		if (order.getCustomer() == this
				&& order.getNumberRemaining() == 0
				&& order.getNumberOfProducts() == order
						.getDeliveredItemsCount()) {
			System.out.println("Complete order that started in "+order.getTimeStamp()+" was delivered to customer in " + AltTimeControllerSingleton.getInstance().getCurrentTimeString());
			System.out.println("Customer " + name + " is happy :)");
		} else {
			throw new OrderDeliveryExcepiton();
		}

	}

}
