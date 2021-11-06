package it.unibo.oop.lab.exception2;

public class TransactionsOverQuotaException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5186015990336694793L;
	private final int transactions;
	
	public TransactionsOverQuotaException(final int transactions) {
		this.transactions = transactions;
	}
	
	public String toString() {
		return this.transactions + "are too many, you cannot perform so many transactions!";
	}
	
	public String getMessage() {
		return this.toString();
	}

}
