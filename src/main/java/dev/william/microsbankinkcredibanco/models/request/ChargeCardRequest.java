package dev.william.microsbankinkcredibanco.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChargeCardRequest {
    private String idCardActivation;
    private BigDecimal balance;

}
