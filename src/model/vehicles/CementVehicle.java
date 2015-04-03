package model.vehicles;

import exceptions.WrongComponentException;
import model.ComponentTypes;

public class CementVehicle extends BaseVehicle {

	/**
	 * @author yanina
	 * Constructs a vehicle that can deliver cement with specified capacity
	 * @param capacity capacity of the delivery vehicle
	 */
	public CementVehicle(double capacity) {
		super(capacity, ComponentTypes.CEMENT);
	}

	/**
	 * @author yanina
	 * Validates that only proper component is requested of this delivery vehicle. In this case it is Cement
	 * @param type type of component requested
	 * @throws WrongComponentException
	 */
	@Override
	public void validateComponentType(ComponentTypes type)
			throws WrongComponentException {
		if (type != this.type){
			throw new WrongComponentException("Cement vehicle cannot deliver " + type);
		}
		
	}
	
	

}
