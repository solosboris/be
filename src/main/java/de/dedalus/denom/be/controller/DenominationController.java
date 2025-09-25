package de.dedalus.denom.be.controller;

import de.dedalus.denom.be.data.model.Amounts;
import de.dedalus.denom.be.data.model.ArgAmount;
import de.dedalus.denom.be.data.model.Denomination;
import de.dedalus.denom.be.data.model.DenominationItem;
import de.dedalus.denom.be.service.DenominationSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DenominationController {

    private final DenominationSevice denominationSevice;

    @PostMapping(
        value = "/amount",
        produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Denomination> provideValues(
        @RequestBody ArgAmount amount
    ) {
        log.info("provideValues {}", amount.getAmount());
        List<DenominationItem> items = denominationSevice.denomination(
            amount.getAmount()
        );
        log.info("provideValues size {}", items.size());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Denomination(items));
    }

    @PostMapping(
        value = "/diff",
        produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Denomination> difference(
        @RequestBody Amounts amounts
    ) {
        log.info(
            "difference {} {}",
            amounts.getPreviousAmount(),
            amounts.getCurrentAmount()
        );
        Denomination denom = denominationSevice.difference(
            amounts.getPreviousAmount(),
            amounts.getCurrentAmount()
        );
        log.info("difference size {}", denom.getItems().size());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(denom);
    }

}