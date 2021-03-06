package com.com.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Dictionary extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime addDate;
    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime editDate;
    @Column(nullable = false, updatable = false)
    private String key;
    private String value;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

}
