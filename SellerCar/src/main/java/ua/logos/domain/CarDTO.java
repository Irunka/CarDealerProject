package ua.logos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class CarDTO {

    private Long id;
    private  String brand;
    private  String model;
    private  String colour;
    private  String volume;
    private  String colour_engine;
    private BigDecimal price;
    private  SellerDTO seller;
}
