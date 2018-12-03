package com.frank.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();
    private Clock clock;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addDeposit(int amount) {
        transactions.add(new Transaction(clock.todayAsString(), amount));
    }

    public void addWithdrawal(int amount) {
        transactions.add(new Transaction(clock.todayAsString(), -amount));
    }

    public List<Transaction> get() {
        return Collections.unmodifiableList(transactions);
    }
}
