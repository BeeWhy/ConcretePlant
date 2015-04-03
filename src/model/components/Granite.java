package model.components;

import model.ComponentTypes;

public class Granite extends BaseComponent{

	/**
	 * Constructs granite component with user-provided amount
	 * The type of component is Granite
	 * @see ComponentTypes
	 * @param quantity double value of the quantity of component we are working with
	 */
	public Granite(double quantity) {
		super(ComponentTypes.GRANITE, quantity);
	}

}
