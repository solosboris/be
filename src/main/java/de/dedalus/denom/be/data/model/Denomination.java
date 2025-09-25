package de.dedalus.denom.be.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Denomination {

    private final List<DenominationItem> items;

}