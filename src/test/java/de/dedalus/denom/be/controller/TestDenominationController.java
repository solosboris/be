package de.dedalus.denom.be.controller;

import de.dedalus.denom.be.data.enums.MoneyItemTypeEnum;
import de.dedalus.denom.be.data.model.Denomination;
import de.dedalus.denom.be.data.model.DenominationItem;
import de.dedalus.denom.be.data.model.MoneyItem;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestDenominationController {

    private RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/api/denom";


    @Test
    @Ignore
    public void testDenominationValues() throws Exception {
        JSONObject moneyAmount = new JSONObject();
        moneyAmount.put("amount", "234.23");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(
            moneyAmount.toString(),
            headers
        );
        Denomination denomination = restTemplate.postForObject(
            url.concat("/amount"),
            request,
            Denomination.class
        );
        List<DenominationItem> items = denomination.getItems();

        assertTrue(items.size() == 7);
        items.forEach(
            denomItem -> log.info(denomItem.toString())
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(20000, MoneyItemTypeEnum.NOTE), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(2000, MoneyItemTypeEnum.NOTE), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(1000, MoneyItemTypeEnum.NOTE), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(200, MoneyItemTypeEnum.COIN), 2
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(20, MoneyItemTypeEnum.COIN), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(2, MoneyItemTypeEnum.COIN), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(1, MoneyItemTypeEnum.COIN), 1
                )
            )
        );
    }

    @Test
    @Ignore
    public void testDifference() throws Exception {
        JSONObject moneyAmounts = new JSONObject();
        moneyAmounts.put("previousAmount", "45.32");
        moneyAmounts.put("currentAmount", "234.23");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(
            moneyAmounts.toString(),
            headers
        );
        Denomination denomination = restTemplate.postForObject(
            url.concat("/diff"),
            request,
            Denomination.class
        );
        List<DenominationItem> items = denomination.getItems();

        assertTrue(items.size() == 7);
        items.forEach(
            denomItem -> log.info(denomItem.toString())
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(20000, MoneyItemTypeEnum.NOTE), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(2000, MoneyItemTypeEnum.NOTE), -1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(1000, MoneyItemTypeEnum.NOTE), 1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(500, MoneyItemTypeEnum.NOTE), -1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(200, MoneyItemTypeEnum.COIN), 2
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(10, MoneyItemTypeEnum.COIN), -1
                )
            )
        );
        assertTrue(
            items.contains(
                new DenominationItem(
                    new MoneyItem(1, MoneyItemTypeEnum.COIN), 1
                )
            )
        );
    }

}