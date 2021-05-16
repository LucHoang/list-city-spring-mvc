package com.hsl.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
//@Table(name = "nations")
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(targetEntity = City.class)
//    private List<City> cities;


    public Nation() {
    }

    public Nation(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
