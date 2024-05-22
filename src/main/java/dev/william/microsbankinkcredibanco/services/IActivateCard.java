package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.ActivateCardRequest;
import dev.william.microsbankinkcredibanco.models.response.ActivateCardResponse;

public interface IActivateCard extends CrudServiceGeneric<ActivateCardRequest, ActivateCardResponse,String> {

        ActivateCardResponse activarCard (ActivateCardRequest request);
}
