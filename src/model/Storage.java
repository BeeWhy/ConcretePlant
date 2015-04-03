package model;

import exceptions.InsufficientSuppliesException;
import exceptions.WrongComponentException;
import model.components.Cement;
import model.components.Granite;
import model.components.IComponent;
import model.components.Sand;
import model.components.Water;
import model.vehicles.BaseVehicle;
import model.vehicles.VehicleFactory;


/**
 * Contains information on avaialble amount of components.
 * When components are not sufficient, they are delivered by special delivery vehicles
 */
public class Storage {
	/**
	 * Amount of available sand component
	 */
	double amntOfSand;
	/**
	 * amount of available granite component
	 */
	double amntOfGranite;
	/**
	 * amount of available cement
	 */
	double amntOfCement;
	/**
	 * amount of available water
	 */
	double amntOfWater;
	/**
	 * available delivery vehicles
	 */
	private final BaseVehicle sandAndGraniteVehicle, cementVehicle, waterVehicle;


	/**
	 *Constructor of the storage that creates delivery vehicles for this storage
	 */

	public Storage() {
		super();
		VehicleFactory f = new VehicleFactory();		
		this.sandAndGraniteVehicle = f.createVehicleByType(ComponentTypes.GRANITE, 14.0);
		this.cementVehicle = f.createVehicleByType(ComponentTypes.CEMENT, 6.0);
		this.waterVehicle = f.createVehicleByType(ComponentTypes.WATER, 6.5);
	}

	/**
	 *Requests the delivery vehicles to bring more component if insufficiency had been determined
	 * @param type type of component
	 * @throws WrongComponentException if wrong component has been requested of a vehicle
	 */
	public void requestMoreAmount(ComponentTypes type) throws WrongComponentException{
		switch (type){
		case SAND:
			sandAndGraniteVehicle.acceptRequest(this, type);
			break;
		case GRANITE:
			sandAndGraniteVehicle.acceptRequest(this, type);
			break;
		case CEMENT:
			cementVehicle.acceptRequest(this, type);
			break;
		case WATER:
			waterVehicle.acceptRequest(this, type);
			break;
		}
	}

	/**
	 * Delivery vehicle will add the amount to available as much as it has delivered
	 * @param amnt amount of delivered component
	 * @param type type of component
	 */
	public void addAmount(double amnt, ComponentTypes type){
		switch (type){
		case SAND:
			amntOfSand += amnt;
			break;
		case GRANITE:
			amntOfGranite += amnt;
			break;
		case CEMENT:
			amntOfCement += amnt;
			break;
		case WATER:
			amntOfWater += amnt;
			break;
		}
		
	}

	public double getAmntOfSand() {
		return amntOfSand;
	}

	public double getAmntOfGranite() {
		return amntOfGranite;
	}


	public double getAmntOfCement() {
		return amntOfCement;
	}


	public double getAmntOfWater() {
		return amntOfWater;
	}

	/**
	 * Use part of the available component amount to the production of the new product
	 * @param amnt
	 * @param comp
	 * @throws InsufficientSuppliesException
	 */
	public void consume(double amnt, IComponent comp) throws InsufficientSuppliesException{
		if (comp instanceof Sand){
			if(amnt > amntOfSand){
				throw new InsufficientSuppliesException("amount if sand missing "+ (amnt - amntOfSand) );
			}
			amntOfSand -= amnt;
		} else if(comp instanceof Granite){
			if (amnt > amntOfGranite){
				throw new InsufficientSuppliesException("Amnt of granite missing " + (amnt - amntOfGranite));
			}
			amntOfGranite -= amnt;
		} else if(comp instanceof Cement){
			if (amnt > amntOfCement){
				throw new InsufficientSuppliesException("Amount of cement missing " + (amnt - amntOfCement));
			}
			amntOfCement -= amnt;
		} else if (comp instanceof Water){
			if (amnt >  amntOfWater){
				throw new InsufficientSuppliesException("Amount of water missing " + (amnt - amntOfWater));
			}
			amntOfWater -= amnt;
		}
		
		
	}
	

}
