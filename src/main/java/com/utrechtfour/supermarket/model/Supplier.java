package com.utrechtfour.supermarket.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class
Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String name;
    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
