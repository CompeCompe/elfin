package ru.eremenko.elfin.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author eremenko
 */
@Document(indexName = "domain")
@Getter
@Setter
public class MailDomain {

    @Id
    private Long id;

    private String name;
}
