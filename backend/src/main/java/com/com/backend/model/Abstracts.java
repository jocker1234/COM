package com.com.backend.model;

import com.com.backend.model.enums.AbstractType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Abstracts extends AbstractEntity {

    @Column(nullable = false)
    private String title;
    @Type(type = "string-array")
    @Column(nullable = false, columnDefinition = "text[]")
    private String[] authors;
    @Column(nullable = false)
    private String tutors;
    @Column(nullable = false)
    private String affiliation;
    @Column(nullable = false)
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AbstractType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
