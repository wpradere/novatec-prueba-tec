package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.AnulationTransactionRequest;
import dev.william.microsbankinkcredibanco.models.response.AnulationTransactionResponse;
import dev.william.microsbankinkcredibanco.util.CreditCardProcess;
import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import dev.william.microsbankinkcredibanco.util.exceptions.IdNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class AnulaTransService implements IAnulaTrans {

    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;
    @Override
    public AnulationTransactionResponse anulationTrnas(AnulationTransactionRequest request) {
        AnulationTransactionResponse response = new AnulationTransactionResponse();
        BigDecimal balanceActual,total=BigDecimal.ZERO;

        var transactionA = transactionRepository.findById(request.getTransactionId()).orElseThrow(()->new IdNotFoundExceptions("transaction"));
        if (transactionA.getStateTransaccion().equals(CreditCardProcess.sucess)){

                if (transactionA.getTypeTransaction().equals(TypeTransaction.Recargar_saldo) || transactionA.getTypeTransaction().equals(TypeTransaction.Compra)) {

                    transactionA.setStateTransaccion(CreditCardProcess.anulada);
                    var cardReturn = creditCardRepository.findById(transactionA.getIdCreditCard()).orElseThrow(() -> new IdNotFoundExceptions("transaction"));
                    Long diference = (ChronoUnit.MINUTES.between(transactionA.getCreateAt(), LocalDateTime.now()) / 60);
                    if (diference > 24) {
                        response.setMessage("Transaction cannot be cancelled becouse time was expire ");
                    }else {
                        balanceActual = cardReturn.getBalance();
                        total = balanceActual.add(transactionA.getValueTransaction());
                        cardReturn.setBalance(total);
                        transactionRepository.save(transactionA);
                        creditCardRepository.save(cardReturn);
                        response.setMessage("Transaction has already been succesfull ");
                        response.setCreateAt(LocalDateTime.now());
                        response.setTransactionId(transactionA.getTransactionId());
                    }

                }else {
                    response.setMessage("Transaction cannot be cancelled ");
                }
        }else {
            response.setMessage("Transaction cannot be cancelled ");
        }

        return response;
    }
}
