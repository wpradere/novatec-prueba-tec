package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.ConsultTransactionRequest;
import dev.william.microsbankinkcredibanco.models.response.ConsultTransactionResponse;

public interface IConsultTransaction extends CrudServiceGeneric<ConsultTransactionRequest, ConsultTransactionResponse,Long>{


            ConsultTransactionResponse checkTrnsaction(ConsultTransactionRequest request);
}
