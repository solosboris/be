package de.dedalus.denom.be.data.model;

import de.dedalus.denom.be.data.enums.MoneyItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MoneyItem {

    private final int cent;
    private final MoneyItemTypeEnum type;

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && (obj instanceof MoneyItem)
                && cent == ((MoneyItem) obj).getCent()
                && type == ((MoneyItem) obj).getType();
    }

}