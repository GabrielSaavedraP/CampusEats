package org.example.campuseats.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name="foodOrders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany()
    private Long customerId;

    @OneToOne()
    private Long productId;

    private Integer quantity;

    private BigDecimal totalAmount;

    private ZonedDateTime createdAt;

    private String status;
}
