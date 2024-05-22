package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.CustomerRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.TransactionPuchaseRequest;
import dev.william.microsbankinkcredibanco.models.response.TransactionPuchaseResponse;
import dev.william.microsbankinkcredibanco.util.CreditCardProcess;
import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import dev.william.microsbankinkcredibanco.util.exceptions.IdNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TransacionPuchaseService implements ITransactionPuchase{

    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    @Override
    public TransactionPuchaseResponse createTransaction(TransactionPuchaseRequest request) {
        TransactionPuchaseResponse response = new TransactionPuchaseResponse();

        var cardTransaction = creditCardRepository.findByIdCardActivation(request.getIdCardActivation()).orElseThrow(()-> new IdNotFoundExceptions("Balance Check"));
        BigDecimal balanceActual,operation,total=BigDecimal.ZERO;

        balanceActual= cardTransaction.getBalance();
        operation = balanceActual.subtract(request.getPrice());
        int comparation = operation.compareTo(total);

        if (balanceActual.equals(BigDecimal.ZERO) || (operation.equals(total))|| (comparation==-1) ){

            var transactionPersist = TransactionEntity.builder()
                    .typeTransaction(TypeTransaction.Activar_Tarjeta)
                    .descriptionTransaction("The purchase was declinate for insufficient funds.  !!! ")
                    .stateTransaccion(CreditCardProcess.decline)
                    .valueTransaction(request.getPrice())
                    .createAt(LocalDateTime.now())
                    .idCustomer(cardTransaction.getCustomer().getDni())
                    .idCreditCard(cardTransaction.getProductId())
                    .build();
            response.setNewBalance(cardTransaction.getBalance());
            response.setMessage("The transaction cannot be completed  for insufficient funds.  ");
            this.transactionRepository.save(transactionPersist);
        }else {
            var transactionPersist = TransactionEntity.builder()
                    .typeTransaction(TypeTransaction.Activar_Tarjeta)
                    .descriptionTransaction("The purchase was successful !!! ")
                    .stateTransaccion(CreditCardProcess.sucess)
                    .valueTransaction(request.getPrice())
                    .createAt(LocalDateTime.now())
                    .idCustomer(cardTransaction.getCustomer().getDni())
                    .idCreditCard(cardTransaction.getProductId())
                    .build();
            this.transactionRepository.save(transactionPersist);
            cardTransaction.setBalance(operation);
            creditCardRepository.save(cardTransaction);
            response.setCreateAt(cardTransaction.getCreateAt());
            response.setValueTransaction(request.getPrice());
            response.setTransactionId(transactionPersist.getTransactionId());
            response.setNewBalance(cardTransaction.getBalance());
            response.setMessage("The transaction was  successful  ");
        }
        return response;
    }
}
