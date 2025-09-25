package de.dedalus.denom.be.data.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Amounts {

    private final String previousAmount;
    private final String currentAmount;

    public String getPreviousAmount() {
        return previousAmount == null || previousAmount.length() < 4 ?
                "0.00" : previousAmount;
    }

    public String getCurrentAmount() {
        return currentAmount == null || currentAmount.length() < 4 ?
                "0.00" : currentAmount;
    }

}