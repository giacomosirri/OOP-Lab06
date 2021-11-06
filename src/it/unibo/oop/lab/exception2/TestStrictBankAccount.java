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

	private static final double AMOUNT_TO_WITHDRAW = 100.00;
	
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
    	BankAccount account1 = new StrictBankAccount(1, 10_000.00, 10);
    	BankAccount account2 = new StrictBankAccount(2, 10_000.00, 10);
    	
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
    		fail();
    	}
    	
    	for (int i = 0; i < 9; i++) {
    		account2.depositFromATM(2, 1.50);
    	}
    	// trying the 11th operation on account 2, should fail
    	try {
    		account2.withdrawFromATM(2, 150.50);
    		fail();
    	} catch (TransactionsOverQuotaException e) {
    		assertNotNull(e.getMessage());
    		System.out.println(e.getMessage());
    	}
    	// check wrong account ID
    	try {
    		account1.withdraw(3, 15_000.00);
    		fail();
    	} catch (WrongAccountHolderException e){
    		assertNotNull(e.getMessage());
    		System.out.println(e.getMessage());
    	}
    	// withdraw 500.00 until account 1 has no money left
    	while (account1.getBalance() > AMOUNT_TO_WITHDRAW) {
    		account1.withdraw(1, AMOUNT_TO_WITHDRAW);
    	}
    	// in account 1 there is too little money left, so it is not possible to withdraw anymore
    	try {
    		account1.withdraw(1, AMOUNT_TO_WITHDRAW);
    		fail();
    	} catch (NotEnoughFoundsException e) {
    		assertNotNull(e.getMessage());
    		System.out.println(e.getMessage());
    	}
    }
}
