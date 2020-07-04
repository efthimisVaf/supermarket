package com.utrechtfour.supermarket.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.views.RestViews;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Product {


    @Column(nullable = false, unique = true)
    @JsonView({RestViews.ProductView.class})
    @Size(min = 13, max = 13)
    private String barcode;
    @Id
    @Column(nullable = false)
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
    @OneToOne (cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @JsonView({RestViews.ProductView.class})
    private Brand brand;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "product_suppliers", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
    @NotEmpty
    @JsonView({RestViews.ProductView.class})
    private List<Supplier> suppliers = new ArrayList<Supplier>();


    public Product(){}

    public Product(String barcode, String name,String description, Long brandId, BigDecimal price){
        this.barcode = barcode;
        this.name = name;
        this.description = description;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = Unit.unit(unit);
    }
}
