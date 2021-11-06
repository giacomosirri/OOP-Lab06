package it.unibo.oop.lab.exception2;

public class WrongAccountHolderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7190620326792084901L;
	private final AccountHolder falseAccount;
	
	public WrongAccountHolderException(AccountHolder account) {
		super();
		this.falseAccount = account;
	}
	
	public String toString() {
		return "Account " + this.falseAccount + " is not authorized to perform any action on this account";
	}
	
	public String getMessage() {
		return this.toString();
	}
}

