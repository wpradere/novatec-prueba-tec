package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.CustomerRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.CheckBalanceRequest;
import dev.william.microsbankinkcredibanco.models.response.ChargeCardResponse;
import dev.william.microsbankinkcredibanco.models.response.CheckBalanceResponse;
import dev.william.microsbankinkcredibanco.util.CreditCardProcess;
import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import dev.william.microsbankinkcredibanco.util.exceptions.IdNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class CheckBalanceService implements ICheckBlance {
    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public CheckBalanceResponse chechCard(CheckBalanceRequest request) {

        var balanceCheck = creditCardRepository.findById(request.getProductId()).orElseThrow(()-> new IdNotFoundExceptions("Balance Check"));
        var transactionPersist = TransactionEntity.builder()
                .typeTransaction(TypeTransaction.Activar_Tarjeta)
                .descriptionTransaction("The card balances consult is  succesfull !!! ")
                .stateTransaccion(CreditCardProcess.sucess)
                .valueTransaction(BigDecimal.ZERO)
                .createAt(LocalDateTime.now())
                .idCustomer(balanceCheck.getCustomer().getDni())
                .idCreditCard(balanceCheck.getProductId())
                .build();
        this.transactionRepository.save(transactionPersist);
        return this.entityToResponse(balanceCheck);
    }

    private CheckBalanceResponse entityToResponse(CreditCardEntity entity){
        var response = new CheckBalanceResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
