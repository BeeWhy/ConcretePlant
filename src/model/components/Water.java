package model.components;

import model.ComponentTypes;

public class Water extends BaseComponent{

	/**
	 * Constructs water component with user-provided amount
	 * The type of component is Water
	 * @see  ComponentTypes
	 * @param quantity double value of the quantity of component we are working with
	 */
	public Water(double quantity) {
		super(ComponentTypes.WATER, quantity);
	}

}
