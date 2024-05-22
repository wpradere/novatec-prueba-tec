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

public class TransactionPuchaseResponse {
    private Long transactionId;
    private BigDecimal valueTransaction;
    private LocalDate createAt;
    private BigDecimal newBalance;
    private String message;
}
