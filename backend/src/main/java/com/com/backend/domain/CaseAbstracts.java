package com.com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class CaseAbstracts extends Abstracts {

    @Column(nullable = false)
    private String background;
    @Column(nullable = false)
    private String caseReport;
    @Column(nullable = false)
    private String conclusions;

}
