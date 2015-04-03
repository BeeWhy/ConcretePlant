package model;

/**
 * @author yanina
 * IEvent is an object that is associated with a particular point of time point on the plant.
 * Such objects are orders, that are associated with the time they were placed and products, that are associated with the time they were produced
 * @see model.product.ConcreteProduct
 * @see Order
 */
public interface IEvent {

	/**
	 * @author yanina
	 * Provides the time when the event has occured
	 * @return the timestamp when the event of the object has occured, such as creation of placement
	 */
	String getTimeStamp();

	/**
	 * @author yanina
	 * The time of event occurence
	 * @param timestamp a current time on the plant, that is provided by time controller singleton
	 * @see util.TimeControllerSingleton
	 * @see util.AltTimeControllerSingleton
	 */
	void setTimeStamp(String timestamp);
}
