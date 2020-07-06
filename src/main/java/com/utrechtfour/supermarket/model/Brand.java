package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @Column(nullable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RestViews.ProductView.class})
    private Long id;
    @JsonView({RestViews.ProductView.class})
    @NotNull
    private String name;
    @OneToOne(mappedBy = "brand", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
