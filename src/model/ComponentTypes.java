package model;

/**
 * @author yanina
 * enum type of 4 available components in the cement: sand, granite, water, cement
 */
public enum ComponentTypes {
	SAND (0.5),
	GRANITE (0.25),
	CEMENT (0.1),
	WATER (0.15);

	/**
	 * @author yanina
	 * Necessary proportion of the component type in the product
	 */
	public final double percentDec;
	
	ComponentTypes(double percent) {
		this.percentDec = percent;
	}

}
