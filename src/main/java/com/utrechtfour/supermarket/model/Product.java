package com.utrechtfour.supermarket.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Product {


    @Column(nullable = false, unique = true)
    private String barcode;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date updateTime;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategories category;
    @Enumerated(EnumType.STRING)
    private VatTariffs vatTarrif;
    @NumberFormat(pattern = "###.##")
    private BigDecimal price;
    @OneToOne
    @NotNull
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
    @ManyToMany(mappedBy = "products")
    private List<Supplier> suppliers;


    public Product(){}

    public Product(String barcode, String name,String description, Long brandId, BigDecimal price){
        this.barcode = barcode;
        this.name = name;
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ProductCategories getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = ProductCategories.category(category);
    }

    public void setCategory(ProductCategories category) {
        this.category = category;
    }
    public VatTariffs getVatTarrif() {
        return vatTarrif;
    }

    public void setVatTarrif(Integer vatTarrif) {
        this.vatTarrif = VatTariffs.tariff(vatTarrif);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
