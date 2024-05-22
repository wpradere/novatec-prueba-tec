package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.ChargeCardRequest;
import dev.william.microsbankinkcredibanco.models.response.ChargeCardResponse;

public interface IChargeCardService extends CrudServiceGeneric<ChargeCardRequest, ChargeCardResponse,Long>{

    ChargeCardResponse cargarTarjeta (ChargeCardRequest request);

}


