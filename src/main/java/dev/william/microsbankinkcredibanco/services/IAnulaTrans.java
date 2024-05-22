package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.AnulationTransactionRequest;
import dev.william.microsbankinkcredibanco.models.response.AnulationTransactionResponse;

public interface IAnulaTrans extends CrudServiceGeneric<AnulationTransactionRequest, AnulationTransactionResponse,Long>{

    AnulationTransactionResponse anulationTrnas(AnulationTransactionRequest request);
}
