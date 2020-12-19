package com.episen.ing3.fise.springbootlockauthentification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Lock {

    private String owner ;

    @CreatedDate
    private LocalDateTime created ;
}
