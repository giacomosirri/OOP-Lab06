package it.unibo.oop.lab.exception2;

public class WrongAccountHolderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7190620326792084901L;
	private final int falseID;
	
	public WrongAccountHolderException(final int userID) {
		super();
		this.falseID = userID;
	}
	
	public String toString() {
		return "Account with ID=" + this.falseID + " is not authorized to perform any action on this account";
	}
	
	public String getMessage() {
		return this.toString();
	}
}

