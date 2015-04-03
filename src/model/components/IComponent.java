package model.components;

public interface IComponent {
	/**
	 * Provides amount of the component we are working with
	 * @see model.ComponentTypes
	 * @return double type quantity of component we are working with in default measurements used in the project
	 */
	double getQuantity();
}
