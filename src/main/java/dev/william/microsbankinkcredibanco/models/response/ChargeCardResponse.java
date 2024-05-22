package dev.william.microsbankinkcredibanco.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChargeCardResponse {
    private String idCardActivation;
    private BigDecimal balance;
    private LocalDate createAt;
}
