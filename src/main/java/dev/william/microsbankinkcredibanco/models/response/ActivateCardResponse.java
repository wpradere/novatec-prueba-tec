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
public class ActivateCardResponse {

    private String idCardActivation;
    private String active;
    private LocalDate createAt;
    private String message;
}
