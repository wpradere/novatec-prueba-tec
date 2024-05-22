package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.ChargeCardRequest;
import dev.william.microsbankinkcredibanco.models.response.ChargeCardResponse;
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
public class ChargeCardService implements IChargeCardService{

    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public ChargeCardResponse cargarTarjeta(ChargeCardRequest request) {

        var carBalance = creditCardRepository.findByIdCardActivation (request.getIdCardActivation()).orElseThrow(()->new IdNotFoundExceptions("Customer"));
        BigDecimal balanceActual,total=BigDecimal.ZERO;
        int dato=carBalance.getBalance().compareTo(BigDecimal.ZERO);

        if(dato== 1|| dato==0){
        balanceActual= carBalance.getBalance();
        total= balanceActual.add(request.getBalance());
        log.info("  suma de balances    " + total);
        carBalance.setBalance(total);
        }

       this.creditCardRepository.save(carBalance);
        var transactionPersist = TransactionEntity.builder()
                .typeTransaction(TypeTransaction.Recargar_saldo)
                .descriptionTransaction("The Balance was charge  succesfull !!! ")
                .stateTransaccion(CreditCardProcess.sucess)
                .valueTransaction(total)
                .createAt(LocalDateTime.now())
                .idCustomer(carBalance.getCustomer().getDni())
                .idCreditCard(carBalance.getProductId())
                .build();
        this.transactionRepository.save(transactionPersist);
        return this.entityToResponse(carBalance);
    }
    private ChargeCardResponse entityToResponse(CreditCardEntity entity){
        var response = new ChargeCardResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
