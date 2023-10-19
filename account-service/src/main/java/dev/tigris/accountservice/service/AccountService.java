package dev.tigris.accountservice.service;

import dev.tigris.accountservice.exception.EntityNotFoundException;
import dev.tigris.accountservice.modal.Account;
import dev.tigris.accountservice.repository.AccountRepository;
import dev.tigris.feignservice.modal.AccountDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountDto getAccount(String id)  {
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account does not exist with id" + id));
        return modelMapper.map(account,AccountDto.class);

    }

    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account=accountRepository.save(account);
        accountDto.setId(account.getId());
        return accountDto;
    }

    public AccountDto updateAccount(AccountDto accountDto, String id) {
        Optional<Account> account = accountRepository.findById(id);

        if(account.isEmpty()){
          throw  new EntityNotFoundException("Account does not exist with id " + id);
        }
        Account updateAccount = account.get();
        updateAccount.setEmail(accountDto.getEmail());
        updateAccount.setUsername(accountDto.getUsername());
        updateAccount.setName(accountDto.getName());
        updateAccount.setSurname(accountDto.getSurname());
        updateAccount.setBirthDate(accountDto.getBirthDate());

        return modelMapper.map(updateAccount,AccountDto.class);

    }

    public Void deleteAccount(String id) {

      accountRepository.deleteById(id);
      return null;
    }

    public List<AccountDto> getAll(Pageable pageable) {
        Slice<Account> accounts = accountRepository.findAll(pageable);
       return accounts.stream()
                .map(account -> modelMapper.map(account,AccountDto.class)).collect(Collectors.toList());

    }
}
