package dev.tigris.tickerservice.repo.elastic;

import dev.tigris.tickerservice.modal.elastic.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel,String> {

}
