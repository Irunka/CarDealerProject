package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "cars")
public class CarEntity extends  BaseEntity {

    @Column(name = "brand",nullable = false)
    private  String brand;

    @Column(name = "model",nullable = false)
    private  String model;

    @Column(name = "colour",nullable = false)
    private  String colour;

    @Column(name = "volume",nullable = false)
    private  String volume;

    @Column(name = "colour_engine",nullable = false)
    private  String colour_engine;

    @Column(name = "price")
    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private  SellerEntity seller;
}
