package com.poslek.springsandbox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Builder
    public City(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    @JsonIgnoreProperties(value = { "city" })
    private Set<SuperHero> superHeroes = new HashSet<>();

}
