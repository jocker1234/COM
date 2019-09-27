package com.com.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class CaseAbstracts extends Abstracts {

    @Column(nullable = false)
    private String background;
    @Column(nullable = false)
    private String caseReport;
    @Column(nullable = false)
    private String conclusions;

}
