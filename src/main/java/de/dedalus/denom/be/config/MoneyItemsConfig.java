package de.dedalus.denom.be.config;

import de.dedalus.denom.be.data.model.MoneyItem;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("items")
@Getter
public class MoneyItemsConfig {

    private final List<MoneyItem> values = new ArrayList<>();

}