package com.utrechtfour.supermarket.model;

public enum ProductCategories {
    UNCATEGORIZED(0),
    STATIONARY(1),
    FRUITS(2);

    private final int categoryId;

    ProductCategories(int categoryId){
        this.categoryId = categoryId;
    }

    static ProductCategories category(int categoryId){
        switch (categoryId) {
            case 0: return ProductCategories.UNCATEGORIZED;
            case 1: return ProductCategories.STATIONARY;
            case 2: return ProductCategories.FRUITS;
            default: return null;
        }
    }
}



