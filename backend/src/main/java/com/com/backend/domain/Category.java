package com.com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Category extends AbstractEntity {

    private String name;

}
