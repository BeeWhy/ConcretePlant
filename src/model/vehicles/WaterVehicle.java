package model.vehicles;

import exceptions.WrongComponentException;
import model.ComponentTypes;

public class WaterVehicle extends BaseVehicle{

	/**
	 * @author yanina
	 * Constructs a vehicle that can deliver water with specified capacity
	 * @param capacity capacity of the delivery vehicle
	 */
	public WaterVehicle(double capacity) {
		super(capacity, ComponentTypes.WATER);
	}

	/**
	 * @author yanina
	 * Validates that only proper component is requested of this delivery vehicle. In this case it is Water
	 * @param type type of component requested
	 * @throws WrongComponentException
	 */
	@Override
	public void validateComponentType(ComponentTypes type)
			throws WrongComponentException {
		if (type != this.type){
			throw new WrongComponentException("Water vehicle cannot deliver component " + type);
		}
	}

}
