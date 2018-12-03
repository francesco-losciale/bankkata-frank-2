package com.frank.kata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {
    public String todayAsString() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
    }
}
