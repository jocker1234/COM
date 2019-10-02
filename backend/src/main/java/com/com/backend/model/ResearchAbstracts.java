package com.com.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ResearchAbstracts extends Abstracts {

    @Column(nullable = false)
    private String introduction;
    @Column(nullable = false)
    private String aimOfTheStudy;
    @Column(nullable = false)
    private String materialAndMethods;
    @Column(nullable = false)
    private String results;
    @Column(nullable = false)
    private String conclusions;

}
