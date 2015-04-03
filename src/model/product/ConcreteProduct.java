package model.product;

import exceptions.ComponentMissingException;
import exceptions.SoldProductException;
import model.IEvent;
import model.components.*;

/**
 * @author yanina
 * Concrete product is the product of out factory.
 * One instance of the product is equal to one loadtruck that is can be delivered to the customer.
 * There is a MAX of 4 load trucks that one plant can produce. That means that 4 instances of Concrete Product per day can be produced
 */
public class ConcreteProduct implements IEvent {
	/**
	 * @author yanina
	 * Sand component used in the product
	 */
	private Sand sand;
	/**
	 * @author yanina
	 * Water component used in the product
	 */
	private Water water;
	/**
	 * @author yanina
	 * cement component used in the product
	 */
	private Cement cement;
	/**
	 * @author yanina
	 * granite component used in the product
	 */
	private Granite granite;
	/**
	 * @author yanina
	 * timestamp when the product was produced
	 */
	private String timeStamp;
	/**
	 * @author yanina
	 * plant's id of the product
	 */
	private int id;

	/**
	 * @author yanina
	 * if the ready product has been sold and delivered to customer, or is currently in delivery, is sold is said to true
	 */
	boolean isSold = false;

	/**
	 * @author yanina
	 * Provides id of the concrete product instance
	 * @return int value of the id of the product.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @author yanina
	 * Sets matching id to the product that has been assigned by the plant
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @author yanina
	 * Adds sand to the product to the mixer when the builder is creating the product
	 * @param sand
	 */
	void setSand(Sand sand) {
		this.sand = sand;
	}

	/**
	 * @author yanina
	 * Adds water to the product to the mixer when the builder is creating the product
	 * @param water
	 */
	void setWater(Water water) {
		this.water = water;
	}

	/**
	 * @author yanina
	 * Adds cement to the product to the mixer when the builder is creating the product
	 * @param cement
	 */
	void setCement(Cement cement) {
		this.cement = cement;
	}

	/**
	 * @author yanina
	 * Adds granite to the product to the mixer when the builder is creating the product
	 * @param granite
	 */
	void setGranite(Granite granite) {
		this.granite = granite;
	}

	/**
	 * @author yanina
	 * Calculates the total amount of components in the product
	 * @return a double value of the total amount of components
	 * @throws ComponentMissingException in case one or more components have not been added to the product
	 */
	double getTotalAmntOfComponents()
			throws ComponentMissingException {
		if (sand == null || granite == null || cement == null || water == null) {
			throw new ComponentMissingException(
					"One or more components are missing in the mixture");
		}
		return sand.getQuantity() + granite.getQuantity()
				+ cement.getQuantity() + water.getQuantity();
	}

	/**
	 * @author yanina
	 * Marks the product as sold, when the product has been delivered or in the process of the delivery to the customer
	 * In this method isSold value is set to true. From this moment we cannot revert it to false
	 * @throws SoldProductException in case a product that has already been sold is attempted to sell again
	 */
	public void sell() throws SoldProductException {
		if (isSold) {
			throw new SoldProductException();
		} else {
			System.out.println("Selling product with timestamp " + timeStamp);
			isSold = true;
		}
	}

	/**
	 * @author yanina
	 * Checks if a given product was sold
	 * @return
	 */
	public boolean isSold() {
		return isSold;
	}

	/**
	 * @author yanina
	 * Provides the time when concrete product was produced
	 * @return
	 */
	@Override
	public String getTimeStamp() {

		return this.timeStamp;
	}

	/**
	 * @author yanina
	 * Sets the time when the product was produced
	 * @param timestamp a current time on the plant, that is provided by time controller singleton
	 */
	@Override
	public void setTimeStamp(String timestamp) {
		this.timeStamp = timestamp;

	}

}
