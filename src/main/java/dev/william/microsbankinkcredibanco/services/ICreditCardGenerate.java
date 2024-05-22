package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.AssignedCardRequest;
import dev.william.microsbankinkcredibanco.models.response.AssignedCardResponse;

public interface ICreditCardGenerate extends CrudServiceGeneric<AssignedCardRequest, AssignedCardResponse,Long>{
    AssignedCardResponse activar(AssignedCardRequest request);
}
