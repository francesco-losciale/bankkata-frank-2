package com.frank.kata;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    private Console console;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        printHeader();
        printLines(transactions);
    }

    private void printLines(List<Transaction> transactions) {
        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions.stream()
            .map(transaction -> transactionAsLine(transaction, runningBalance))
            .collect(Collectors.toCollection(LinkedList::new))
            .descendingIterator()
            .forEachRemaining(console::printLine);
    }

    private void printHeader() {
        console.printLine("DATE | AMOUNT | BALANCE");
    }

    private String transactionAsLine(Transaction transaction, AtomicInteger runningBalance) {
        return transaction.getDate() + " | " + decimalFormat.format(transaction.getAmount()) + " | " + decimalFormat.format(runningBalance.addAndGet(transaction.getAmount()));
    }
}
