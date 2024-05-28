package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
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
    public  ActivateCardResponse activarCard(ActivateCardRequest request) {
        ActivateCardResponse response = new ActivateCardResponse();
        var cardActivate = creditCardRepository.findByIdCardActivation(request.getIdCardActivation()).orElseThrow(()->new IdNotFoundExceptions("Card"));


        if ( cardActivate.isActive()==false){
            cardActivate.setActive(true);

                    response.setCreateAt(cardActivate.getCreateAt());
                    response.setActive("true");
                    response.setMessage("The card was Create succesfull !!!!");
                    response.setIdCardActivation(cardActivate.getIdCardActivation());
                    TransactionProses(cardActivate,response.getMessage());
        }else{
            response.setIdCardActivation(cardActivate.getIdCardActivation());
            response.setActive("true");
            response.setCreateAt(cardActivate.getCreateAt());
            response.setMessage("The card  was not Create ");
            TransactionProses(cardActivate,response.getMessage());
        }
        return response;
    }

    private  void TransactionProses ( CreditCardEntity  cardActive , String message  ){

        var transactionPersist = TransactionEntity.builder()
                .typeTransaction(TypeTransaction.Activar_Tarjeta)
                .descriptionTransaction(message)
                .stateTransaccion(CreditCardProcess.sucess)
                .valueTransaction(BigDecimal.ZERO)
                .createAt(LocalDateTime.now())
                .idCustomer(cardActive.getCustomer().getDni())
                .idCreditCard(cardActive.getProductId())
                .build();
        this.transactionRepository.save(transactionPersist);

    }


}
