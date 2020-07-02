package com.utrechtfour.supermarket.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String name;
    @OneToOne(mappedBy = "brand", cascade = CascadeType.ALL)
    private Product product;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
