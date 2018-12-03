package com.frank.kata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeatureTest {

    @Mock Console console;
    @Mock Clock clock;
    private StatementPrinter statementPrinter;
    private TransactionRepository transactionRepository;

    @Before
    public void initialize() {
        transactionRepository = new TransactionRepository(clock);
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void testPrintStatementCorrectFormat() {
        given(clock.todayAsString()).willReturn("01/04/2014", "02/04/2014", "10/04/2014");
        Account account = new Account(transactionRepository, statementPrinter);
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        account.printStatement();
        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
