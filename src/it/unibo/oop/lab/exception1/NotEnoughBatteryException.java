package it.unibo.oop.lab.exception1;

/**
 * Represents an exception occurring when a robot does not have enough battery to
 * perform an action.
 * 
 */
public class NotEnoughBatteryException extends IllegalStateException {

	private static final long serialVersionUID = -288979685441144222L;
	private final double battery;
	
	public NotEnoughBatteryException(double battery) {
		super();
		this.battery = battery;
	}
	/**
	 * 
	 */
	
}
