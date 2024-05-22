package dev.william.microsbankinkcredibanco.models.request;

import dev.william.microsbankinkcredibanco.util.TypeCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GenerateCardRequest {
    private Long productId;
    private TypeCard typeCreditCard;
    private String customerid;
}
