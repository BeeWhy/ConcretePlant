package model.vehicles;

import model.ComponentTypes;

/**
 * Based on the Factory OO pattern this class is intended to construct a proper vehicle based on the provided type and capacity
 */
public class VehicleFactory {

    /**
     * Created a delivery vehicle based on the provided type of component it can deliver and capacity
     * @param type Type of component the vehicle can deliver
     * @param loadCapacity Capacity of delivery vehicle in double type of default measurement used in the project
     * @return the delivery vehicle that was requested to create
     */
    public BaseVehicle createVehicleByType(ComponentTypes type, double loadCapacity) {
        switch (type) {
            case SAND:
            case GRANITE:
                return new SandAndGraniteVehicle(loadCapacity);

            case CEMENT:
                return new CementVehicle(loadCapacity);
            case WATER:
                return new WaterVehicle(loadCapacity);


        }

        return null;
    }
}