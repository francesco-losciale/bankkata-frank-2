package com.frank.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterTest {

    @Mock
    Console console;

    @Test
    public void testStatementPrintedOnConsole() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01/04/2014", 1000),
                new Transaction("02/04/2014", -100),
                new Transaction("10/04/2014", 500)
        );
        StatementPrinter statementPrinter = new StatementPrinter(console);
        statementPrinter.print(transactions);
        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
        verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
    }

    @Test
    public void testStatementPrintedOnConsoleOrdered() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01/04/2014", 1000),
                new Transaction("02/04/2014", -100),
                new Transaction("10/04/2014", 500)
        );
        StatementPrinter statementPrinter = new StatementPrinter(console);
        statementPrinter.print(transactions);
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }

}
