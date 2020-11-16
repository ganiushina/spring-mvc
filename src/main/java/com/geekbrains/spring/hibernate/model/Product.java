package com.geekbrains.spring.hibernate.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "coast")
    private int coast;


    public Product(Long id, String name, int coast) {
        this.id = id;
        this.name = name;
        this.coast = coast;
    }

    public Product() {
    }


    @OneToMany(mappedBy = "product")
    Set<Order> orderSet;

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

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
