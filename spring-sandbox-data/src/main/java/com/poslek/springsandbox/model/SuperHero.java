package com.poslek.springsandbox.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "superheroes")
public class SuperHero extends BaseEntity {

    @Builder
    public SuperHero(Long id, String firstName, String lastName, String heroName, String city) {
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

    @Column(name = "city")
    private String city;
}
