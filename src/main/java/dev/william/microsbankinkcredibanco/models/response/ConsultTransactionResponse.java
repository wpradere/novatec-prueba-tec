package dev.william.microsbankinkcredibanco.models.response;

import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ConsultTransactionResponse {
    private Long transactionId;
    private TypeTransaction typeTransaction;
    private String descriptionTransaction;
    private BigDecimal valueTransaction;
    private LocalDateTime createAt;
    private String idCustomer;
    private Long idCreditCard;
}
