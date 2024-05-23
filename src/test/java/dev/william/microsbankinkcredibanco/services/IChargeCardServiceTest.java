package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.CustomerEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.request.ChargeCardRequest;
import dev.william.microsbankinkcredibanco.models.response.ChargeCardResponse;
import dev.william.microsbankinkcredibanco.util.TypeCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IChargeCardServiceTest {

    @Autowired
    private ChargeCardService chargeCardService;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void setUp() {
        CustomerEntity client =
                CustomerEntity.builder()
                        .dni("254621")
                        .name("sagan")
                        .lastname("carl")
                        .phoneNumber("4567965431")
                        .totalCreditCard(2)
                        .build();


        CreditCardEntity credit =

                CreditCardEntity.builder()
                        .productId(1000020L)
                        .idCardActivation("1000031392493558")
                        .typeCreditCard(TypeCard.debito)
                        .active(false)
                        .expiry_date("2023-08-22")
                        .balance(BigDecimal.valueOf(4500L))
                        .createAt(LocalDate.now())
                        .customer(client)
                        .build();

        Mockito.when(creditCardRepository.findByIdCardActivation("1000031392493558")).thenReturn(Optional.of(credit));
    }
    @Test
    public void chargeMoneyCard (){
        ChargeCardRequest request = new ChargeCardRequest();
        request.setIdCardActivation("1000031392493558");
        request.setBalance(BigDecimal.valueOf(4500L));
        ChargeCardResponse cardCharge = chargeCardService.cargarTarjeta(request);
        assertEquals(request.getIdCardActivation(),cardCharge.getIdCardActivation());

    }
}