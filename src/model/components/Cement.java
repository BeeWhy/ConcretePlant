package model.components;

import model.ComponentTypes;

public class Cement extends BaseComponent {

	/**
	 * @author yanina
	 * Constructs cement component with user-provided amount
	 * The type of component is Cement
	 * @see ComponentTypes
	 * @param quantity double value of the quantity of component we are working with
	 */
	public Cement(double quantity) {
		super(ComponentTypes.CEMENT, quantity);
	}

}
