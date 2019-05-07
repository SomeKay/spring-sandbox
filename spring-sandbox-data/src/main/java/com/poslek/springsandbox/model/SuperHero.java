package com.poslek.springsandbox.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "superheroes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
    @JoinColumn(name = "city_id")
    private City city;
}
