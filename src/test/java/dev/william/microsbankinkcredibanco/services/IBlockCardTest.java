package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.CustomerEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.request.BlockCardRequest;

import dev.william.microsbankinkcredibanco.models.response.BlockCardResponse;
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
class IBlockCardTest {

    @Autowired
    private BlockCardService blockCardService;

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
                        .idCardActivation("1000031392493520")
                        .typeCreditCard(TypeCard.debito)
                        .active(false)
                        .expiry_date("2023-08-22")
                        .balance(BigDecimal.ZERO)
                        .createAt(LocalDate.now())
                        .customer(client)
                        .build();

        Mockito.when(creditCardRepository.findById (1000020L)).thenReturn(Optional.of(credit));
    }

    @Test
    public void BlockCardIdFound(){
        BlockCardRequest request = new BlockCardRequest();
        request.setProductId(1000020L);
        BlockCardResponse cardResponse = blockCardService.blockCard(request);
        assertEquals(request.getProductId(),cardResponse.getIdProduct());
    }
}