package dev.tigris.tickerservice.modal;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
public abstract class BasedEntity {

    @CreatedDate
    private Date createdAt;

    private Date updateAt;
}
