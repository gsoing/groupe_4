package com.episen.ing3.fise.springbootlockauthentification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Documents {

    @Id
    String documentId;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    private String title ;
    private String creator;
    private String editor;
    private String body ;
    private enum status {VALIDATED,CREATED}
}
