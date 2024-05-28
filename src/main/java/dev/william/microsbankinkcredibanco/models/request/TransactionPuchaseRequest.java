package dev.william.microsbankinkcredibanco.models.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class TransactionPuchaseRequest {

    @NotBlank(message = "customerid is mandatory ")
    @Size(min =16 , max = 16 , message = "idCardActivation must contains at least 16 digits")
    private String idCardActivation;
    @Min(value = 1)
    private BigDecimal price;
}
