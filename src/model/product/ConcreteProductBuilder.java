package model.product;

import util.AltTimeControllerSingleton;
import exceptions.ComponentMissingException;
import exceptions.InsufficientSuppliesException;
import exceptions.WrongComponentException;
import exceptions.WrongQuantityOfComponentException;
import model.ComponentTypes;
import model.Storage;
import model.components.Cement;
import model.components.Granite;
import model.components.IComponent;
import model.components.Sand;
import model.components.Water;

public class ConcreteProductBuilder {
	private final ConcreteProduct p;
	private IComponent sand;
	private IComponent water;
	private IComponent granite;
	private IComponent cement;
	private final Storage storage;

	/**
	 * @author yanina
	 * Constructs ConcreteProduct Builder. Builder is used to produce concrete in proper order and proportions.
	 * Builder maintains id of the product that the plant has assigned to it, therefore it is a required parameter in constructor.
	 * Builder is tied to the storage of the plant.
	 * @param storage Storage from where the components of the product will be delivered
	 * @param productId id to the product that will be created.
	 */
	public ConcreteProductBuilder(Storage storage, int productId) {
		super();
		this.storage = storage;
		this.p = new ConcreteProduct();
		p.setId(productId);
	}
	/**
	 *
	 * @author yanina
	 * Adds first component to the concrete mixer and
	 * returns the builder of one concrete product when the first element (expected sand) has been added to it.
	 *
	 * @param sand third component of the product. Contains info about amount and type of component to add
	 * @return the builder of on concrete product
	 * @throws WrongComponentException
	 * @throws InsufficientSuppliesException If amount of supply contained in @param water was insufficient
	 */
	public ConcreteProductBuilder addFirst(Sand sand) throws WrongComponentException, InsufficientSuppliesException{
		if (!(sand instanceof Sand)){
			throw new WrongComponentException("First component should be sand");
		}

		storage.consume(sand.getQuantity(), sand);
		this.sand = sand;
		p.setSand(sand);
		return this;
	}

	/**
	 *
	 * @author yanina
	 * Adds second component to the concrete mixer and
	 * returns the builder of one concrete product when the second element (expected granite) has been added to it.
	 *
	 * @param granite second component of the product. Contains info about amount and type of component to add
	 * @return the builder of on concrete product
	 * @throws WrongComponentException
	 * @throws InsufficientSuppliesException If amount of supply contained in @param water was insufficient
	 */
	public ConcreteProductBuilder addSecond(Granite granite) throws WrongComponentException, InsufficientSuppliesException{
		if (!(granite instanceof Granite)){
			throw new WrongComponentException("Second component should be granite");
		}
		if (sand == null){
			throw new WrongComponentException("First component is missing");
		}

		storage.consume(granite.getQuantity(), granite);
		this.granite = granite;
		p.setGranite(granite);
		return this;
	}

	/**
	 * @author yanina
	 * Adds third component to the concrete mixer and
	 * returns the builder of one concrete product when the third element (expected cement) has been added to it.
	 *
	 * @param cement third component of the product. Contains info about amount and type of component to add
	 * @return the builder of on concrete product
	 * @throws WrongComponentException
	 * @throws InsufficientSuppliesException If amount of supply contained in @param water was insufficient
	 */
	public ConcreteProductBuilder addThird(Cement cement) throws WrongComponentException, InsufficientSuppliesException{
		if (!(cement instanceof Cement)){
			throw new WrongComponentException("Third component should be cement");
		}
		if ( (sand == null || granite == null)){
			throw new WrongComponentException("First or Second component is missing");
		}
		storage.consume(cement.getQuantity(), cement);
		this.cement = cement;
		p.setCement(cement);
		return this;
	}

	/**
	 * @author yanina
	 * Adds fourth component to the concrete mixer and
	 * returns the builder of one concrete product when the fourth element (expected water) has been added to it.
	 *
	 * @param water fourth component of the product. contains info about amount and type of component to add
	 * @return the builder of on concrete product
	 * @throws WrongComponentException
	 * @throws InsufficientSuppliesException If amount of supply contained in @param water was insufficient
	 */
	public ConcreteProductBuilder addFourth(Water water) throws WrongComponentException, InsufficientSuppliesException{
		if (!(water instanceof Water)){
			throw new WrongComponentException("Fourth component should be water");
		}
		if ( (sand == null || cement == null|| granite == null)){
			throw new WrongComponentException("First or Second component is missing");
		}
		storage.consume(water.getQuantity(), water);
		this.water = water;
		p.setWater(water);
		return this;
	}

	/**
	 * @author yanina
	 * Produces the final concrete product when all the components have been added
	 * It sets a time stamp on the time of production of concrete and places an id on the final product that the plant has assigned to it.
	 *
	 * @return complete instance of one concrete product
	 * @throws WrongQuantityOfComponentException if proportions of one or more components were faulty
	 * @throws ComponentMissingException if one or more components have not been added
	 */
	public ConcreteProduct mixUp() throws WrongQuantityOfComponentException, ComponentMissingException {
		if (!checkQuantitiesOfComponents()){
			throw new WrongQuantityOfComponentException("Proportions in product creation were missed!");
		}
		// add timeStamp
		AltTimeControllerSingleton.getInstance().setTimeStampOnEvent(p);
		System.out.println("A product completed with id " + p.getId()+ "and timestamp: " + p.getTimeStamp());
		return p;
	}


	private boolean checkQuantitiesOfComponents() throws ComponentMissingException {
		double mass = p.getTotalAmntOfComponents();
		return ((sand.getQuantity() / mass == ComponentTypes.SAND.percentDec) &&
				(granite.getQuantity() / mass == ComponentTypes.GRANITE.percentDec) &&
				(cement.getQuantity() / mass == ComponentTypes.CEMENT.percentDec) &&
				water.getQuantity() / mass == ComponentTypes.WATER.percentDec);
	}
}
