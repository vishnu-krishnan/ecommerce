package com.aithmetic.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@io.swagger.v3.oas.annotations.media.Schema(description = "Response containing product information")

public class ProductResponse {

    @Schema(description = "Unique identifier of the product", example = "123", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(message = "ProductId cannot be null")
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
