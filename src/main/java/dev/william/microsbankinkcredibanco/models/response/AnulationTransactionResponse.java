package dev.william.microsbankinkcredibanco.models.response;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnulationTransactionResponse {

    private LocalDateTime createAt;
    private Long transactionId;
    private String message;

}
