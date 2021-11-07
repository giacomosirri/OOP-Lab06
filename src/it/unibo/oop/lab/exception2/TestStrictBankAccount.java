package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * JUnit test to test
 * {@link StrictBankAccount}.
 * 
 */
public final class TestStrictBankAccount {

	private static final double INITIAL_AMOUNT = 10_000.00;
	private static final double AMOUNT_TO_WITHDRAW = 100.00;
	private static final String UNEXPECTED_EXCEPTION = "Everything should be working up until this point";
	private static final String EXCEPTION_WAS_EXPECTED = "An exception was expected here";
    /**
     * Used to test Exceptions on {@link StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
        /*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         * 
         */
    	AccountHolder user1 = new AccountHolder("Marco", "Lucchi", 1);
    	AccountHolder user2 = new AccountHolder("Gennaro", "Esposito", 2);
    	BankAccount account1 = new StrictBankAccount(1, INITIAL_AMOUNT, 10);
    	BankAccount account2 = new StrictBankAccount(2, INITIAL_AMOUNT, 10);
    	
    	/*
         * 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         * 
         */
    	
    	// random operations, checking that no unexpected exceptions are thrown
    	try {
    		account1.deposit(1, 2_500.00);
    		account2.deposit(2, 10_000.00);
    		account2.withdrawFromATM(2, 18_000.00);
    		account1.deposit(1, 2_000.00);
    	} catch (RuntimeException e) {
    		fail(UNEXPECTED_EXCEPTION);
    	}
    	
    	// performing 10 operations on account 2, the 11th should fail
    	for (int i = 0; i < 9; i++) {
    		try {
    			account2.depositFromATM(2, 1.50);
    		} catch (RuntimeException e) {
    			fail(UNEXPECTED_EXCEPTION);
    		}
    	}
    	try {
    		account2.withdrawFromATM(2, 150.50);
    		fail(EXCEPTION_WAS_EXPECTED);
    	} catch (TransactionsOverQuotaException e) {
    		assertNotNull(e.getMessage());
    		System.out.println(e.getMessage());
    	}
    	
    	// check wrong account ID
    	try {
    		account1.withdraw(3, 15_000.00);
    		fail(EXCEPTION_WAS_EXPECTED);
    	} catch (WrongAccountHolderException e){
    		assertNotNull(e.getMessage());
    		System.out.println(e.getMessage());
    	}
    	
    	// withdraw until account 1 has no money left, then it won't be possible to withdraw anymore
    	while (account1.getBalance() > AMOUNT_TO_WITHDRAW) {
    		try {
    			account1.withdraw(1, AMOUNT_TO_WITHDRAW);
    		} catch (RuntimeException e) {
    			fail(UNEXPECTED_EXCEPTION);
    		}
    	}
    	try {
    		account1.withdraw(1, AMOUNT_TO_WITHDRAW);
    		fail(EXCEPTION_WAS_EXPECTED);
    	} catch (NotEnoughFoundsException e) {
    		assertNotNull(e.getMessage());
    		System.out.println(e.getMessage());
    	}
    }
}
