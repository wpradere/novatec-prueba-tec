package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.ConsultTransactionRequest;
import dev.william.microsbankinkcredibanco.models.response.ConsultTransactionResponse;
import dev.william.microsbankinkcredibanco.util.exceptions.IdNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ConsultTransactionServices implements IConsultTransaction {


    private final TransactionRepository transactionRepository;
    @Override
    public ConsultTransactionResponse checkTrnsaction(ConsultTransactionRequest request) {

        var transaction = transactionRepository.findById(request.getTransactionId()).orElseThrow(()->new IdNotFoundExceptions("Customer"));
        return this.entityToResponse(transaction);
    }

    private ConsultTransactionResponse entityToResponse(TransactionEntity entity){
        var response = new ConsultTransactionResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
