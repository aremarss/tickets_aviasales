package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private final TicketRepository repository = new TicketRepository();
    private final TicketManager manager = new TicketManager(repository);

    private final Ticket firstTicket = new Ticket(1, 6448, "MOW", "KZN", 95);
    private final Ticket secondTicket = new Ticket(2, 6853, "MOW", "KZN", 105);
    private final Ticket thirdTicket = new Ticket(3, 6798, "MOW", "KZN", 100);
    private final Ticket fourthTicket = new Ticket(4, 6886, "MOW", "KZN", 94);
    private final Ticket fifthTicket = new Ticket(5, 3188, "MOW", "LED", 90);
    private final Ticket sixthTicket = new Ticket(6, 3248, "MOW", "LED", 95);
    private final Ticket seventhTicket = new Ticket(7, 3248, "MOW", "LED", 90);
    private final Ticket eighthTicket = new Ticket(8, 3542, "MOW", "LED", 100);
    private final Ticket ninthTicket = new Ticket(9, 3498, "MOW", "LED", 90);

    @BeforeEach
    void add(){
        manager.add(firstTicket);
        manager.add(secondTicket);
        manager.add(thirdTicket);
        manager.add(fourthTicket);
        manager.add(fifthTicket);
        manager.add(sixthTicket);
        manager.add(seventhTicket);
        manager.add(eighthTicket);
        manager.add(ninthTicket);
    }

    @Test
    void shouldSortChosenTickets(){
        Ticket[] expected = new Ticket[]{fifthTicket, ninthTicket, firstTicket, fourthTicket};
        Ticket[] actual = new Ticket[]{firstTicket, fourthTicket, fifthTicket, ninthTicket};

        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndSortMOWToKZNTickets() {
        Ticket[] expected = new Ticket[]{firstTicket, thirdTicket, secondTicket, fourthTicket};
        Ticket[] actual = manager.getAll("MOW", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndSortMOWToLEDTickets(){
        Ticket[] expected = new Ticket[]{fifthTicket, sixthTicket, seventhTicket, ninthTicket, eighthTicket};
        Ticket[] actual = manager.getAll("MOW", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndSortByDepartureAirport(){
        Ticket[] expected = new Ticket[]{fifthTicket, sixthTicket, seventhTicket, ninthTicket, eighthTicket, firstTicket, thirdTicket, secondTicket, fourthTicket};
        Ticket[] actual = manager.searchBy("MOW");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndSortByArrivalAirport(){
        Ticket[] expected = new Ticket[]{fifthTicket, sixthTicket, seventhTicket, ninthTicket, eighthTicket};
        Ticket[] actual = manager.searchBy("LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFound() {
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchBy("Someone");

        assertArrayEquals(expected, actual);
    }
}
