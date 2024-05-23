package dev.william.microsbankinkcredibanco.models.repository;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.entities.CustomerEntity;
import dev.william.microsbankinkcredibanco.util.TypeCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    TestEntityManager testEntityManager;

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

        testEntityManager.persist(credit);
    }

    @Test
    public void findByIdCardActivationIfFind(){
        Optional <CreditCardEntity> creditCard = creditCardRepository.findByIdCardActivation("1000031392493520");
        assertEquals(creditCard.get().getIdCardActivation(),"1000031392493520");
    }

    @Test
    public void findByIdCardActivationIfnotFind(){
        Optional <CreditCardEntity> creditCard = creditCardRepository.findByIdCardActivation("1000031393520");
        assertEquals(creditCard,Optional.empty());
    }

}