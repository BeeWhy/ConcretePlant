package model;

import java.util.LinkedList;

import model.product.ConcreteProduct;
import exceptions.EmptyOrderException;

/**
 * Mechanism of customers communicating to the concrete plant and back.
 * Contains all the information about the order.
 * Uses visitor pattern
 */
public class Order implements IEvent {
	/**
	 * Number of requested loadtrucks of concrete
	 */
	private final int numberOfProducts;
	/**
	 * Time when the order was placed
	 */
	private String timeStamp;
	/**
	 * Customer who originated the order
	 */
	private ICustomer customer;
	/**
	 * List of completed products
	 */
	private LinkedList<ConcreteProduct> items;


	/**
	 * Constructs the new order from a Customer with a number of requested loadtruck products
	 * @param numberOfProducts requested number of loadtrucks of concrete product
	 * @param customer customer who initiated the order
	 * @throws EmptyOrderException if customer didn't request any products with this order and the order is empty.
	 * Such order will be rejected
	 */
	public Order(int numberOfProducts, ICustomer customer) throws EmptyOrderException {
		super();
		if (numberOfProducts <1){throw new EmptyOrderException("Order has to contain at least 1 product to be processed");}
		this.numberOfProducts = numberOfProducts;
		this.customer = customer;
		items = new LinkedList<ConcreteProduct>();
	}


	/**
	 * Provides the time when the order was placed
	 * @return
	 */
	@Override
	public String getTimeStamp() {
		return timeStamp;
	}


	/**
	 * (Typically the plant) sets the time when the order was accepted
	 * @param timestamp a current time on the plant, that is provided by time controller singleton
	 */
	@Override
	public void setTimeStamp(String timestamp) {
		this.timeStamp = timestamp;
		System.out.println("The time of order is " + timestamp);
		
	}


	/**
	 * Provides instance of the customer who originated the order
	 * @return
	 */
	public ICustomer getCustomer() {
		return customer;
	}


	/**
	 * Provides infomation how many products were requested
	 * @return int value of the number of requested products
	 */
	public int getNumberOfProducts() {
		return numberOfProducts;
	}


	/**
	 * This method is called when one product is ready and it is added to the existing order
	 * @param item a ready loadtruck of concrete product
	 */
	public void acceptDelivery(ConcreteProduct item){
		items.add(item);
		System.out.println("Product with ID " + item.getId() + "has been delivered to customer "+ customer.toString());
		System.out.println("Items remaining to deliver: "+ getNumberRemaining());
		System.out.println("Items delivered so far to this order: "+ getDeliveredItemsCount());
	}

	/**
	 * Provides infomation how many products have already been delivered
	 * @return int value on how many products have been delivered
	 */
	public int getDeliveredItemsCount(){
		return items.size();
		
	}

	/**
	 * Provides number of products that are still to be delivered
	 * @return int value of how many products still need to be delivered
	 */
	public int getNumberRemaining(){
		return numberOfProducts - items.size();
	}
	


}
