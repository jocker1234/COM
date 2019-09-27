package com.com.backend.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Category extends AbstractEntity {

    private String name;

}
