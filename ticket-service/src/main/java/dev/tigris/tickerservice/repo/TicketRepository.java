package dev.tigris.tickerservice.repo;

import dev.tigris.tickerservice.modal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {

}
