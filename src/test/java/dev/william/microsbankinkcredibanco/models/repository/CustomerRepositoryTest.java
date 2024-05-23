package dev.william.microsbankinkcredibanco.models.repository;

import dev.william.microsbankinkcredibanco.entities.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {

        CustomerEntity client =
                CustomerEntity.builder()
                        .dni("1154621")
                        .name("sagan")
                        .lastname("carl")
                        .phoneNumber("4567965431")
                        .totalCreditCard(2)
                        .build();

        testEntityManager.persist(client);

    }

    @Test
    public void findByIdclient (){
        Optional<CustomerEntity> customer = customerRepository.findByDni("1154621");
       assertEquals(customer.get().getDni(),"1154621");

    }

    @Test
    public void findByIdNotclient (){
        Optional<CustomerEntity> customer = customerRepository.findByDni("15462");
        assertEquals(customer.isPresent(), false);
    }


}