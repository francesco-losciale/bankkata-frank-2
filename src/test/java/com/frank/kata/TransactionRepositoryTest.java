package com.frank.kata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryTest {

    @Mock Clock clock;
    private TransactionRepository transactionRepository;

    @Before
    public void initialize() {
        this.transactionRepository = new TransactionRepository(clock);
    }

    @Test
    public void testStoreDeposit() {
        given(clock.todayAsString()).willReturn(getDateAsString());
        transactionRepository.addDeposit(100);
        List<Transaction> transactions = transactionRepository.get();
        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0).getDate(), is(getDateAsString()));
    }

    private String getDateAsString() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
    }
}
