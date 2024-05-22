package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.GenerateCardRequest;
import dev.william.microsbankinkcredibanco.models.response.GenerateCardResponse;

public interface ICreateGenericCard extends CrudServiceGeneric<GenerateCardRequest, GenerateCardResponse,Long> {

    GenerateCardResponse crearTarjeta (GenerateCardRequest request);
}
