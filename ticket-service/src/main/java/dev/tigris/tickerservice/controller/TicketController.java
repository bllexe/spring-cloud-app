package dev.tigris.tickerservice.controller;

import dev.tigris.tickerservice.dto.TicketDto;
import dev.tigris.tickerservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    ResponseEntity<TicketDto>  saveTicket(@RequestBody TicketDto ticketDto){

        return new ResponseEntity<>(ticketService.saveTicket(ticketDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<TicketDto> updateTicket(@RequestBody TicketDto ticketDto,@PathVariable String id){
        return new ResponseEntity<>(ticketService.update(ticketDto,id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<TicketDto> getTicketById(@PathVariable String id){
      return new ResponseEntity<>(ticketService.getById(id),HttpStatus.OK);
    }

}
