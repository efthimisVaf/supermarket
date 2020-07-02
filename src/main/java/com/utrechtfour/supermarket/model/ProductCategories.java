package com.utrechtfour.supermarket.model;

public enum ProductCategories {
    STATIONARY(0),
    FRUITS(1);

    private final int categoryId;

    ProductCategories(int categoryId){
        this.categoryId = categoryId;
    }

    static ProductCategories category(int categoryId){
        switch (categoryId) {
            case 0: return ProductCategories.STATIONARY;
            case 1: return ProductCategories.FRUITS;
            default: return null;
        }
    }
}



