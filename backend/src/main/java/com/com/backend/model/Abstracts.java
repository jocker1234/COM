package com.com.backend.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
public class Abstracts extends AbstractEntity {

    @Column(nullable = false)
    private String title;
    @Type(type = "string-array")
    @Column(nullable = false, columnDefinition = "text[]")
    private String[] authors;
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
