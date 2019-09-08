package com.com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ResearchAbstracts extends Abstracts {

    @Column(nullable = false)
    private String introdution;
    @Column(nullable = false)
    private String aimOfTheStudy;
    @Column(nullable = false)
    private String materialAndMethods;
    @Column(nullable = false)
    private String results;
    @Column(nullable = false)
    private String conclusions;

}
