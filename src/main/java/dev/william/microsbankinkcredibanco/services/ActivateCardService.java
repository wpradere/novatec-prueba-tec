package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.CustomerRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.ActivateCardRequest;
import dev.william.microsbankinkcredibanco.models.response.ActivateCardResponse;
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
public class ActivateCardService implements IActivateCard {


    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;
    public ActivateCardResponse activarCard(ActivateCardRequest request) {
        ActivateCardResponse response = new ActivateCardResponse();
        var cardActivate = creditCardRepository.findByIdCardActivation(request.getIdCardActivation()).orElseThrow(()->new IdNotFoundExceptions("Tarjeta"));


        if ( cardActivate.isActive()==false){
            cardActivate.setActive(true);
            var transactionPersist = TransactionEntity.builder()
                    .typeTransaction(TypeTransaction.Activar_Tarjeta)
                    .descriptionTransaction("The card is activate succesfull !!! ")
                    .stateTransaccion(CreditCardProcess.sucess)
                    .valueTransaction(BigDecimal.ZERO)
                    .createAt(LocalDateTime.now())
                    .idCustomer(cardActivate.getCustomer().getDni())
                    .idCreditCard(cardActivate.getProductId())
                    .build();
                    this.transactionRepository.save(transactionPersist);
                    response.setCreateAt(cardActivate.getCreateAt());
                    response.setActive("true");
                    response.setMessage("The card was active succesfull !!!!");
        }else{
            response.setMessage("The card  was not activated ");
        }
        return response;
    }

    private ActivateCardResponse entityToResponse(CreditCardEntity entity){
        var response = new ActivateCardResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }



}
