package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.CheckBalanceRequest;
import dev.william.microsbankinkcredibanco.models.response.CheckBalanceResponse;

public interface ICheckBlance extends CrudServiceGeneric<CheckBalanceRequest, CheckBalanceResponse,Long>{

        CheckBalanceResponse chechCard(CheckBalanceRequest request);
}
