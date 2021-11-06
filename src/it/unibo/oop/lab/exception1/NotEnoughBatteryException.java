package it.unibo.oop.lab.exception1;

/**
 * Represents an exception occurring when a robot does not have enough battery to
 * perform an action.
 * 
 */
public class NotEnoughBatteryException extends IllegalStateException {
	/* Extending IllegalStateException allows to inherit its public or protected methods,
	 * some of which are inherited from RuntimeException, which in turn extends Exception.
	 * In general, the higher you go in a class hierarchy the broader the classes are, so
	 * in this case it is much preferable to choose a more specific class which suits the
	 * needs of the domain, as IllegalStateException does. 
     */
	
	private static final long serialVersionUID = -288979685441144222L;
	private final double battery;
	
	/**
	 * 
	 */
	public NotEnoughBatteryException(double battery) {
		super();
		this.battery = battery;
	}

	
    /**
     * 
     * @return the string representation of instances of this class
     */
    @Override
    public String toString() {
        return "The level of battery left (" + this.battery + ") is not enough to perform the action required";
    }

    @Override
    public String getMessage() {
        return this.toString();
    }
}
