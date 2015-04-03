package model.vehicles;

import exceptions.WrongComponentException;
import model.ComponentTypes;

public class SandAndGraniteVehicle extends BaseVehicle {

	/**
	 * @author yanina
	 * Constructs a vehicle that can deliver sand OR granitee with specified capacity
	 * @param capacity capacity of the delivery vehicle
	 */
	public SandAndGraniteVehicle(double capacity) {
		super(capacity);		
	}

	/**
	 * @author yanina
	 * Validates that only proper component is requested of this delivery vehicle. In this case it is either Sand or Granite
	 * @param type type of component requested
	 * @throws WrongComponentException
	 */
	@Override
	public void validateComponentType(ComponentTypes type)
			throws WrongComponentException {
		System.out.println("Requested component is " + type);
		if (!(type ==ComponentTypes.SAND || type == ComponentTypes.GRANITE)){
			throw new WrongComponentException("Sand-and-granite vehilce cannot deliver this component " + type);
		}
		
	}



}
