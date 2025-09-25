package de.dedalus.denom.be.service;

import de.dedalus.denom.be.config.MoneyItemsConfig;
import de.dedalus.denom.be.data.model.Denomination;
import de.dedalus.denom.be.data.model.DenominationItem;
import de.dedalus.denom.be.data.model.MoneyItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DenominationSevice {

    private final MoneyItemsConfig moneyItemsConfig;

    private static int getCents(String amount) {
        String before = amount.substring(0, amount.length() - 3);
        String after = amount.charAt(amount.length() - 2) == '0' ?
                amount.substring(amount.length() - 1):
                amount.substring(amount.length() - 2);
        log.info("before {} after {}", before, after);
        return Integer.parseInt(before) * 100 + Integer.parseInt(after);
    }

    private static Map<MoneyItem, Integer> mapToKeyValue(
        List<DenominationItem> items
    ) {
        return items
                .stream()
                .collect(
                    Collectors.toMap(
                        DenominationItem::getMoneyItem,
                        DenominationItem::getAmount
                    )
                );
    }

    public List<DenominationItem> denomination(String amount) {
        final int[] centAmount = { DenominationSevice.getCents(amount) };
        List<DenominationItem> items = new ArrayList<>();
        moneyItemsConfig
            .getValues()
            .forEach(moneyItem -> {
                int div = centAmount[0] / moneyItem.getCent();
                if (div > 0) {
                    items.add(new DenominationItem(moneyItem, div));
                    centAmount[0] -= div * moneyItem.getCent();
                }
        });
        return items;
    }

    public Denomination difference(
        String previousAmount,
        String currentAmount
    ) {
        Map<MoneyItem, Integer> prevValues =
            DenominationSevice.mapToKeyValue(
                denomination(previousAmount)
            );
        Map<MoneyItem, Integer> currentValues =
            DenominationSevice.mapToKeyValue(
                denomination(currentAmount)
            );
        Set<MoneyItem> denomKeys = new HashSet<>(
            prevValues.keySet()
        );
        denomKeys.addAll(
            currentValues.keySet()
        );
        List<MoneyItem> allKeys = denomKeys
            .stream()
            .sorted(
                Comparator.comparingInt(MoneyItem::getCent).reversed()
            ).toList();

        return new Denomination(
            allKeys
                .stream()
                .map(moneyItem ->
                    new DenominationItem(
                        moneyItem,
                        currentValues.getOrDefault(moneyItem, 0) - prevValues.getOrDefault(moneyItem, 0)
                    )
            ).filter(
                denominationItem -> denominationItem.getAmount() != 0
            ).sorted(
                Comparator.comparingInt(DenominationItem::getAmount).reversed()
            ).collect(Collectors.toList())
        );
    }

}