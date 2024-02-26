package com.aithmetic.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request for creating a new product")
public class ProductRequest {

    @Schema(description = "Unique identifier of the product", example = "123", accessMode = Schema.AccessMode.READ_ONLY)
    private String productId;

    @Schema(description = "Name of the product", example = "Sample Product")
    private String name;

    @Schema(description = "Description of the product", example = "This is a sample product.")
    private String description;

    @Schema(description = "Price of the product", example = "19.99")
    private BigDecimal price;

    @Schema(description = "Quantity of the product", minimum = "0", example = "100")
    private int quantity;
}
