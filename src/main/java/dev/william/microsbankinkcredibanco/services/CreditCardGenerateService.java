package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.AssignedCardRequest;
import dev.william.microsbankinkcredibanco.models.response.AssignedCardResponse;
import dev.william.microsbankinkcredibanco.util.CreditCardProcess;
import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import dev.william.microsbankinkcredibanco.util.exceptions.IdNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CreditCardGenerateService implements ICreditCardGenerate {


    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public AssignedCardResponse activar(AssignedCardRequest request) {
        var cardActive = creditCardRepository.findById(request.getProductId()).orElseThrow(()->new IdNotFoundExceptions("Card"));
        AssignedCardResponse responseChangeState = new AssignedCardResponse();
        LocalDate fechaVencimiento = LocalDate.now().plusYears(3);
        if (cardActive.isActive()==true){
            responseChangeState.setMessasge("This card is  acitvate ");
            TransactionProses(cardActive,responseChangeState.getMessasge());
        }else {
            if (cardActive.getIdCardActivation()== null){
                    String processed=  fechaVencimiento.getMonth().getValue()+"/"+ fechaVencimiento.getYear();

                    Long id = cardActive.getProductId();

                    Random rm = new Random();
                    Long max = 9999999999L;
                    Long min = 999999999L;
                    Long number = rm.nextInt((int) (max-min))+min;
                    String idStr = String.valueOf(id);
                    String numberSrt =String.valueOf(number);
                    String idActivate = idStr+numberSrt;
                    cardActive.setExpiry_date(processed);
                    cardActive.setActive(false);
                    cardActive.setIdCardActivation(idActivate);
                    cardActive.setCreateAt(LocalDate.now());
                    responseChangeState.setIdCardActivation(cardActive.getIdCardActivation());
                    responseChangeState.setMessasge("The number of Cars already assigned ");
                    TransactionProses(cardActive,responseChangeState.getMessasge());
            }else {
                responseChangeState.setIdCardActivation(cardActive.getIdCardActivation());
                responseChangeState.setMessasge("The number of Cars was  assigned bad transaction ");
                TransactionProses(cardActive,responseChangeState.getMessasge());
            }

        }
        this.creditCardRepository.save(cardActive);
        return responseChangeState;
    }

    private AssignedCardResponse entityToResponse(CreditCardEntity entity){
        var response = new AssignedCardResponse();
        BeanUtils.copyProperties(entity,response);
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
