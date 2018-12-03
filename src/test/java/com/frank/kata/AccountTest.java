package com.frank.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Mock TransactionRepository transactionRepository;
    @Mock StatementPrinter statementPrinter;

    @Test
    public void testStoreDeposit() {
        Account account = new Account(transactionRepository, statementPrinter);
        account.deposit(1000);
        verify(transactionRepository).addDeposit(1000);
    }

    @Test
    public void testStoreWithdraw() {
        Account account = new Account(transactionRepository, statementPrinter);
        account.withdraw(100);
        verify(transactionRepository).addWithdrawal(100);
    }

    @Test
    public void testPrintStatementExecuted() {
        given(transactionRepository.get()).willReturn(Arrays.asList(
                new Transaction("12/05/2015", 1000),
                new Transaction("12/06/2015", 100),
                new Transaction("22/05/2015", 90)
        ));
        Account account = new Account(transactionRepository, statementPrinter);
        account.printStatement();
        verify(statementPrinter).print(transactionRepository.get());
    }

}
