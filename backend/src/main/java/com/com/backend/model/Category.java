package com.com.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Category extends AbstractEntity {

    @Column(nullable = false)
    private String name;

}
