package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.request.TransactionPuchaseRequest;
import dev.william.microsbankinkcredibanco.models.response.TransactionPuchaseResponse;

public interface ITransactionPuchase extends CrudServiceGeneric<TransactionPuchaseRequest, TransactionPuchaseResponse,String>{

        TransactionPuchaseResponse createTransaction(TransactionPuchaseRequest request);

}
