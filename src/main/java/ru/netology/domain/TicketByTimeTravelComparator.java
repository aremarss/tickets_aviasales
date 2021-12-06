package ru.netology.domain;

import java.util.Comparator;

public class TicketByTimeTravelComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getTimeTravel() - o2.getTimeTravel();
    }
}