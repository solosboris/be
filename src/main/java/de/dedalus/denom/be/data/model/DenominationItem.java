package de.dedalus.denom.be.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DenominationItem {

    private MoneyItem moneyItem;
    private int amount;

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && (obj instanceof DenominationItem)
                && amount == ((DenominationItem) obj).getAmount()
                && moneyItem.equals(
                    ((DenominationItem) obj).getMoneyItem()
                );
    }
}