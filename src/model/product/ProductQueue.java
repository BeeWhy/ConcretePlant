package model.product;

import java.util.LinkedList;

/**
 * Product Queue is a line of products that are currently not expired after production and contains sold unexpired products and products available for sale.
 * There are max 4 products in the queue.
 * Once the product expired, it is replaced by a newly created product, it also means that when the new product was created
 * it goes into the queuse and the last added product is removed and marked as expired.
 *
 * All newly created products are added to the queue to ensure accurate information about expired products.
 */
public class ProductQueue {
	public final LinkedList<ConcreteProduct> fifo = new LinkedList<ConcreteProduct>();

	/**
	 * Adds a newly created product to the queue
	 * @param p the new concrete product that was created
	 */
	public void put(ConcreteProduct p) {
		if (fifo.size() == 4) {
			ConcreteProduct toBeOut = fifo.removeFirst();// when it's removed it
															// is expired
															// because
															// it takes equal amnt of time to create
															// new product
			if (toBeOut.isSold) {
				System.out.println("Product with ID " + toBeOut.getId() + " and time stamp:" + toBeOut.getTimeStamp()
						+ "was sold and didn't expire :)");
			} else {
				System.out.println("Product with ID " + toBeOut.getId() +  " and time stamp:" + toBeOut.getTimeStamp()
						+ "expired"); 
			}
		}
		fifo.add(p);
		System.out.println("Product with ID " + p.getId() + " and timestamp " + p.getTimeStamp()+ " was added to available products");
	}


}
