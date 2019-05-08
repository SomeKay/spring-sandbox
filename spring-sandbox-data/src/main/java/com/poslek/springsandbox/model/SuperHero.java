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
@Entity
@Table(name = "superheroes")
public class SuperHero extends BaseEntity {

    @Builder
    public SuperHero(Long id, String firstName, String lastName, String heroName, City city) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.heroName = heroName;
        this.city = city;
    }

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "heroName")
    private String heroName;

    @ManyToOne
    @JsonIgnoreProperties(value = { "superHeroes" })
    private City city;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "superhero_powers", joinColumns = @JoinColumn(name = "superhero_id"),
            inverseJoinColumns = @JoinColumn(name = "power_id"))
    @JsonIgnoreProperties(value = { "superHeroes" })
    private Set<Power> powers = new HashSet<>();
}
