package dev.tigris.accountservice.controller;


import dev.tigris.accountservice.service.AccountService;
import dev.tigris.feignservice.modal.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") String id){

      return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto account){

       return ResponseEntity.ok(accountService.saveAccount(account));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto account,@PathVariable("id") String id){

       return ResponseEntity.ok(accountService.updateAccount(account,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){

       return new ResponseEntity<>(accountService.deleteAccount(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll(Pageable pageable){
        return new ResponseEntity<>(accountService.getAll(pageable),HttpStatus.OK);
    }


}
