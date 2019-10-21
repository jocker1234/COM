package com.com.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class CaseAbstracts extends Abstracts {

    @Column(nullable = false)
    private String background;
    @Column(nullable = false)
    private String caseReport;
    @Column(nullable = false)
    private String conclusions;

}
