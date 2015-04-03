package model.components;

import model.ComponentTypes;

public class Sand extends BaseComponent{
	/**
	 * Constructs sand component with user-provided amount
	 * The type of component is Sand
	 * @see  ComponentTypes
	 * @param quantity double value of the quantity of component we are working with
	 */
	public Sand(double quantity) {
		super(ComponentTypes.SAND, quantity);
	}

}
