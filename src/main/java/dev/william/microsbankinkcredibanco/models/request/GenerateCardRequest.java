package dev.william.microsbankinkcredibanco.models.request;

import dev.william.microsbankinkcredibanco.util.TypeCard;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GenerateCardRequest {

    @NotNull(message = "productId is mandatory ")
    @Min(value = 100000, message = "Min value is  6 digits ")
    @Max(value =999999, message = "max value was 6 digits")
    private Long productId;
    private TypeCard typeCreditCard;
    @NotBlank(message = "customerid is mandatory ")
    private String customerid;
}
