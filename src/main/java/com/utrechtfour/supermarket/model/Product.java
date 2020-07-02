package com.utrechtfour.supermarket.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Product {

    @Id
    @Column(nullable = false)
    private Long barcode;
    @NotBlank
    private String name;
    private String description;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date updateTime;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private ProductCategories category;
    @ElementCollection
    private List<Integer> supplierIds = new ArrayList<Integer>();
    @Enumerated(EnumType.ORDINAL)
    private VatTariffs vatTarrif;
    @NotNull
    private Integer brandId;

    public Product(){}

    public Product(Long barcode, String name,String description, Integer brandId){
        this.barcode = barcode;
        this.name = name;
        this.description = description;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
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

    public List<Integer> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(Integer supplierId) {
        this.supplierIds.add(supplierId);
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public VatTariffs getVatTarrif() {
        return vatTarrif;
    }

    public void setVatTarrif(Integer vatTarrif) {
        this.vatTarrif = VatTariffs.tariff(vatTarrif);
    }
}
