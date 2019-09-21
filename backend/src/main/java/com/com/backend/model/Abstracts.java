package com.com.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class Abstracts extends AbstractEntity {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String authors;
    @Column(nullable = false)
    private String tutors;
    @Column(nullable = false)
    private String status;
    //@Column(nullable = false)
    private String type;

    //@Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
