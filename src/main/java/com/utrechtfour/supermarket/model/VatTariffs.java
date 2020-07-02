package com.utrechtfour.supermarket.model;

public enum VatTariffs {
    NONE(0),
    ZERO(1),
    LOW(2),
    HIGH(3);

    private final int tariffId;

    VatTariffs(int tariffId){
        this.tariffId = tariffId;
    }

    static VatTariffs tariff(int tariffId){
        switch (tariffId) {
            case 0: return VatTariffs.NONE;
            case 1: return VatTariffs.ZERO;
            case 2: return VatTariffs.LOW;
            case 3: return VatTariffs.HIGH;
            default: return null;
        }
    }
}
