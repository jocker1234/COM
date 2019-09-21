package com.com.backend.model;

import com.com.backend.model.enums.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table
public class Authorities extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role roleName;

    @ManyToMany(mappedBy = "authoritiesSet")
    private Set<Users> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authorities)) return false;
        if (!super.equals(o)) return false;
        Authorities that = (Authorities) o;
        return roleName == that.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName);
    }
}
