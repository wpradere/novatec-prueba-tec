package dev.william.microsbankinkcredibanco.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnulationTransactionRequest {
    private String idCardActivation;
    private Long transactionId;
}