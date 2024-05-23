package dev.william.microsbankinkcredibanco.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CheckBalanceResponse {
    private Long productId;
    private BigDecimal balance;
    private String idCardActivation;
}
