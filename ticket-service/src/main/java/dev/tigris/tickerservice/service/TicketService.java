package dev.tigris.tickerservice.service;

import dev.tigris.feignservice.client.AccountServiceClient;
import dev.tigris.feignservice.modal.AccountDto;
import dev.tigris.tickerservice.dto.TicketDto;
import dev.tigris.tickerservice.modal.PriorityType;
import dev.tigris.tickerservice.modal.Ticket;
import dev.tigris.tickerservice.modal.TicketStatus;
import dev.tigris.tickerservice.modal.elastic.TicketModel;
import dev.tigris.tickerservice.repo.TicketRepository;
import dev.tigris.tickerservice.repo.elastic.TicketElasticRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final TicketElasticRepository ticketElasticRepository;
    private final AccountServiceClient accountServiceClient;

    @Transactional
    public TicketDto saveTicket(TicketDto ticketDto){

        Ticket ticket=new Ticket();
        //Account api confirm does exist has assignee
        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());


        ticket.setDescription(ticket.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());

        Ticket savedTicket = ticketRepository.save(ticket);

        //create TicketModel for elasticSearch

        TicketModel ticketModel = TicketModel.builder()
                .description(savedTicket.getDescription())
                .notes(savedTicket.getNotes())
                .id(savedTicket.getId())
                .assignee(accountDtoResponseEntity.getBody().getNameSurname())
                .priorityType(ticket.getPriorityType().name())
                .ticketStatus(ticket.getTicketStatus().name())
                .ticketDate(ticket.getTicketDate()).build();
        //saving elasticSearch
        ticketElasticRepository.save(ticketModel);
        ticketDto.setId(ticket.getId());
        return ticketDto;
    }

    public TicketDto update(TicketDto ticketDto ,String id){

        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty()){
            throw  new EntityNotFoundException("Ticket does not exist");
        }
        Ticket updatedTicket = ticket.get();
        updatedTicket.setTicketDate(ticketDto.getTicketDate());
        updatedTicket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        updatedTicket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        updatedTicket.setNotes(ticketDto.getNotes());
        updatedTicket.setDescription(ticketDto.getDescription());
        updatedTicket.setAssignee(ticketDto.getAssignee());
        return modelMapper.map(updatedTicket,TicketDto.class);
    }

    public TicketDto getById(String ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket does not found"));
        return modelMapper.map(ticket,TicketDto.class);
    }
}
