package dev.tigris.accountservice.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "accounts")
public class Account {

    @PrimaryKey
    private String id= UUID.randomUUID().toString();
    private String username;
    private String name;
    private String surname;
    private Date birthDate;
    private String email;
    private String password;
    private Date createdAt;
    private Boolean active;
}
