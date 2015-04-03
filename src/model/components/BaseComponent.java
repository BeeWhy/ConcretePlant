package model.components;

import model.ComponentTypes;

public abstract class BaseComponent implements IComponent{
	
	private final ComponentTypes type;
	private final double quantity;

	/**
	 * Default constructor for component of concrete
	 * @param type on of the 4 types of components: sand, granite, water, cement.
	 * @see ComponentTypes
	 * @param quantity specifies quantinty of the chunk of component we are working with
	 */
	BaseComponent(ComponentTypes type, double quantity) {
		super();
		this.type = type;
		this.quantity = quantity;
	}

	/**
	 * Provides amount of the chunk of component we are working with
	 * @return a double type value of the quantity of component
	 */
	public double getQuantity() {
		return quantity;
	}	

}
