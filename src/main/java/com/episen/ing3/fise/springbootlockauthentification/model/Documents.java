package com.episen.ing3.fise.springbootlockauthentification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Slf4j
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Document
// C'est un document donc pas de pluriel ici
public class Documents {

    @PersistenceConstructor
    public Documents(){

    }

    @Id
    String documentId;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    private String title ;
    private String creator;
    private String editor;
    private String body;
    private Status status;
    public enum Status {VALIDATED,CREATED}
}
