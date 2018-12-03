package com.frank.kata;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClockTest {

    @Test
    public void testClock() {
        Clock clock = new Clock();
        assertThat(clock.todayAsString(), is(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now())));
    }
}
