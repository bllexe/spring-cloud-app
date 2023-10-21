package dev.tigris.apiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {

    @GetMapping("/account")
    public String accountFallBack(){
        return "Account Service does not available";
    }

    @GetMapping("/ticket")
    public String ticketFallBack(){
        return "Ticket Service is not available";
    }
}
