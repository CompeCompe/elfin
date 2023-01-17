package ru.eremenko.elfin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.eremenko.elfin.entity.MailDomain;

/**
 * @author eremenko
 */
@Repository
public interface MailDomainRepository extends ElasticsearchRepository<MailDomain, Long> {
    Page<MailDomain> findByName(String name, Pageable pageable);
}
