package model.vehicles;

import exceptions.WrongComponentException;
import model.ComponentTypes;
import model.Storage;

/**
 * Base class for all delivery vehicles that deliver components of the concrete product
 */
public abstract class BaseVehicle {

    /**
     * @author yanina
     * A double-type capacity of a vehicle
     */
    private final double capacity; //how much of component 1 vehicle can deliver
    /**
     * @author yanina
     *Type of component tha vehicle can carry.
     * @see ComponentTypes
     */
    ComponentTypes type;

    /**
     * @author yanina
     * Default constructor of a delivery vehicle. Typically proper vehicles are created by the VehicleFactory
     * @see VehicleFactory
     * @param capacity typical amount of component that vehicles can deliver to the storage
     * @param type type of component that this vehicle delivers
     */
    BaseVehicle(double capacity, ComponentTypes type) {
        super();
        this.capacity = capacity;
        this.type = type;
    }

    /**
     * @author yanina
     * In case a vehicle can carry multiple type of components we only specify its capacity.
     * However, make sure to check with documentation for such vehicle to know which components are allowed.
     * @param capacity
     */
    BaseVehicle(double capacity) {
        super();
        this.capacity = capacity;
    }

    /**
     * @author yanina
     * Accepts a request from storage to deliver the component.
     * According to visitor patter we accept req from storage to deliver component and once we are done call the storage method with the delivery amnt

     * @param storage reference to the storage that has made the request and to where the component needs to be delivered
     * @param type type of component requested
     * @throws WrongComponentException if component requested doesn't match the type of the vehicle
     */
    public void acceptRequest(Storage storage, ComponentTypes type) throws WrongComponentException {
        this.type = type;
        validateComponentType(type);
        deliverAmount(storage);
    }

    /**
     * @author yanina
     * Ensures that only allowed by this vehicle component is requested to this vehicle
     * @param type type of component requested
     * @throws WrongComponentException if component requested doesn't match the type of the vehicle
     */
    public abstract void validateComponentType(ComponentTypes type) throws WrongComponentException;

    /**
     * @author yanina
     * Delivers amount of component to the storage
     * @param toStorage Instance of storage to which the component is delivered
     */
    public void deliverAmount(Storage toStorage) {
        toStorage.addAmount(capacity, this.type);
    }

    /**
     * @author yanina
     * Provides capacity of the vehile
     * @return double-type value of the capacity of the vehicle
     */
    public double getCapacity() {
        return capacity;
    }
}
