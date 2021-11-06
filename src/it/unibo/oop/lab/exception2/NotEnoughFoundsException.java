package it.unibo.oop.lab.exception2;

public class NotEnoughFoundsException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1018751054348870270L;
	private final double founds;
	
	public NotEnoughFoundsException(final double founds) {
		this.founds = founds;
	}
	
	public String toString() {
		return this.founds + " is not enough money to perform this action";
	}
	
	public String getMessage() {
		return this.toString();
	}
	
}
