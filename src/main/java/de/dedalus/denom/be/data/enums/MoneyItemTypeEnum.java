package de.dedalus.denom.be.data.enums;

public enum MoneyItemTypeEnum {

    COIN("COIN"),
    NOTE("NOTE");

    private String name;

    MoneyItemTypeEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}