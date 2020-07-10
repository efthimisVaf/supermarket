package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

@Entity
@DynamicUpdate
public class Product {


    @Column(nullable = false, unique = true)
    @JsonView({RestViews.ProductView.class})
    @Size(min = 13, max = 13)
    private String barcode;
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RestViews.ProductView.class})
    private Long id;
    @NotBlank
    @JsonView({RestViews.ProductView.class})
    private String name;
    private String description;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date updateTime;
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonView({RestViews.ProductView.class})
    private ProductCategories category;
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonView(RestViews.ProductView.class)
    private VatTariff vatTarrif;
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonView(RestViews.ProductView.class)
    private Unit unit;
    @NumberFormat(pattern = "000.00")
    @JsonView({RestViews.ProductView.class})
    private BigDecimal price;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonView({RestViews.ProductView.class})
    private Brand brand;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "product_suppliers", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    @JsonView({RestViews.ProductView.class})
    private List<Supplier> suppliers = new ArrayList<>();



    public Product(){}



    public Product(@Size(min = 13, max = 13) String barcode, @NotBlank String name, String description, Date creationTime, Date updateTime, @NotNull ProductCategories category, VatTariff vatTarrif, @NotNull Unit unit, BigDecimal price, @NotNull Brand brand, @NotNull List<Supplier> suppliers) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.category = category;
        this.vatTarrif = vatTarrif;
        this.unit = unit;
        this.price = price;
        this.brand = brand;
        this.suppliers = suppliers;
    }

    public Long getId() {
        return id;
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

    public VatTariff getVatTarrif() {
        return vatTarrif;
    }

    public void setVatTarrif(Integer vatTarrif) {
        this.vatTarrif = VatTariff.tariff(vatTarrif);
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

    public void addSupplier (Supplier supplier){
        getSuppliers().add(supplier);
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = Unit.unit(unit);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
