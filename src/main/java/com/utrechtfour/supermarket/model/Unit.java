package com.utrechtfour.supermarket.model;

public enum Unit {
    UNSPECIFIED(0),
    PC(1),
    KG(2);

    private final int unitId;

    Unit(int unitId) {
        this.unitId = unitId;
    }

    static Unit unit(int unitId){
        switch (unitId) {
            case 0: return Unit.UNSPECIFIED;
            case 1: return Unit.PC;
            case 2: return Unit.KG;
            default: return null;
        }
    }

    public int getUnitId() {
        return unitId;
    }
}
