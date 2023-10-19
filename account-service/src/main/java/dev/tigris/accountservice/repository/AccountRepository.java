package dev.tigris.accountservice.repository;

import dev.tigris.accountservice.modal.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AccountRepository extends CassandraRepository<Account,String> {


}
