package dev.tigris.tickerservice.modal;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BasedEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String description;
    private String notes;
    private String assignee; //came from Account service
    private Date ticketDate;
    @Enumerated
    private PriorityType priorityType;
    @Enumerated
    private TicketStatus ticketStatus;
}
