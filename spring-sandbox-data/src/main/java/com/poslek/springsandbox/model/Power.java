package com.poslek.springsandbox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "powers")
public class Power extends BaseEntity {

    @Builder
    public Power(Long id, String name, Float strength) {
        super(id);
        this.name = name;
        this.strength = strength;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "strength")
    private Float strength;

    @ManyToMany(mappedBy = "powers")
    @JsonIgnoreProperties(value = { "powers" })
    private Set<SuperHero> superHeroes;

}
