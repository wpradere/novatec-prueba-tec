package dev.william.microsbankinkcredibanco.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.william.microsbankinkcredibanco.models.request.ActivateCardRequest;
import dev.william.microsbankinkcredibanco.models.request.AnulationTransactionRequest;
import dev.william.microsbankinkcredibanco.models.request.ChargeCardRequest;
import dev.william.microsbankinkcredibanco.models.request.TransactionPuchaseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class CreditCardControllerTest {

    private final static String URL_ACTIVATE="/card/enroll";
    private final static String URL_ASIGNAR="card/{productId}/number";

    private final static String URL_CHANGECARD="/card/balance";
    private final static String URL_TRANSACTION="/card/transaction/puchase";
    private final static String URL_TRANSACTION_ANULATION="/card/transaction/anulation";

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void asignar() throws Exception {
        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.get(URL_ACTIVATE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());

    }

    @Test
    void actiCard() throws Exception {
        ActivateCardRequest request = buildRequest();
        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.post(URL_ACTIVATE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE).content(mapJson(request)))
                        .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());
    }

    @Test
    void bloquear() {
    }

    @Test
    void cargarBalance() throws Exception {
        ChargeCardRequest request = buildRequest1();

        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.post(URL_CHANGECARD)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE).content(mapJson(request)))
                .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());
    }

    @Test
    void checkBalance() {
    }

    @Test
    void activateC() throws Exception {

        TransactionPuchaseRequest request = buildRequest2();

        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.post(URL_TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE).content(mapJson(request)))
                .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());
    }

    @Test
    void checkTrnasaction() {
    }

    @Test
    void anulacion() throws Exception {
        AnulationTransactionRequest request = buildRequest3();

        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.post(URL_TRANSACTION_ANULATION)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE).content(mapJson(request)))
                .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());


    }

    private String mapJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private ActivateCardRequest buildRequest(){
        ActivateCardRequest request =
                ActivateCardRequest.builder()
                        .idCardActivation("1000031392493519")
                        .build();
        return request;
    }
    private ChargeCardRequest buildRequest1(){
        ChargeCardRequest request =
                ChargeCardRequest.builder()
                        .idCardActivation("8885521389396885")
                        .balance(BigDecimal.valueOf(500L))
                        .build();
        return request;
    }
    private TransactionPuchaseRequest buildRequest2(){
        TransactionPuchaseRequest request =
                TransactionPuchaseRequest.builder()
                        .idCardActivation("8885521389396885")
                        .price(BigDecimal.valueOf(700L))
                        .build();
        return request;
    }

    private AnulationTransactionRequest buildRequest3(){
        AnulationTransactionRequest request =
                AnulationTransactionRequest.builder()
                        .idCardActivation("8885521389396885")
                        .transactionId(24L)
                        .build();
        return request;
    }
}