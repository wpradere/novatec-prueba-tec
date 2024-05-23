package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.CustomerEntity;
import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.ConsultTransactionRequest;
import dev.william.microsbankinkcredibanco.models.response.ConsultTransactionResponse;
import dev.william.microsbankinkcredibanco.util.CreditCardProcess;
import dev.william.microsbankinkcredibanco.util.TypeCard;
import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IConsultTransactionTest {

    @Autowired
    private ConsultTransactionServices consultTransactionServices;


    @MockBean
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {

        TransactionEntity transLocal =
                TransactionEntity.builder()
                        .transactionId(105252L)
                        .typeTransaction(TypeTransaction.Compra)
                        .descriptionTransaction("avance de tarjeta de credito")
                        .stateTransaccion(CreditCardProcess.sucess)
                        .valueTransaction(BigDecimal.valueOf(50000L))
                        .createAt(LocalDateTime.now())
                        .idCustomer("2564314")
                        .idCreditCard(100003L).build();
        Mockito.when(transactionRepository.findById(105252L)).thenReturn(Optional.of(transLocal));

    }
    @Test
    public void findTransactionWhitId(){

        ConsultTransactionRequest request = new ConsultTransactionRequest();
        request.setTransactionId(105252L);

        ConsultTransactionResponse response = consultTransactionServices.checkTrnsaction(request);
        assertEquals(request.getTransactionId(),response.getTransactionId());

    }
}