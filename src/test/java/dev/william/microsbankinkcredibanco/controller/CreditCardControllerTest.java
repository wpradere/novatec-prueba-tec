package dev.william.microsbankinkcredibanco.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.william.microsbankinkcredibanco.models.request.*;
import dev.william.microsbankinkcredibanco.util.TypeCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
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
    private final static String URL_ASIGNAR="/card/888552/number";

    private final static String URL_CHANGECARD="/card/balance";
    private final static String URL_TRANSACTION="/card/transaction/puchase";
    private final static String URL_TRANSACTION_ANULATION="/card/transaction/anulation";
    private final static String URL_BlOQUEAR="/card/100001";
    private final static String URL_CHECKBALANCE="/card/balance/887997";
    private final static String URL_CHECKTRANSACTION="/card/transaction/30";
    private final static String URL_CAEDCREATE="/card/create";

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void insertar () throws Exception {
        GenerateCardRequest request = buildRequest4();


        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.get(URL_CAEDCREATE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE).content(mapJson(request)))
                .andReturn();
        assertEquals(400,mockMvcResulta.getResponse().getStatus());

    }



    @Test
    void asignar() throws Exception {
        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.get(URL_ASIGNAR)

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
    void bloquear() throws Exception {

        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.delete(URL_BlOQUEAR)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());
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
    void checkBalance() throws Exception {
        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.get(URL_CHECKBALANCE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());
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
    void checkTrnasaction() throws Exception {
        MvcResult mockMvcResulta = mockMvc.perform(MockMvcRequestBuilders.get(URL_CHECKTRANSACTION)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn();
        assertEquals(200,mockMvcResulta.getResponse().getStatus());

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

    private GenerateCardRequest buildRequest4(){
        GenerateCardRequest request =
                GenerateCardRequest.builder()
                        .productId(125522L)
                        .typeCreditCard(TypeCard.debito)
                        .customerid("")
                        .build();
        return request;
    }
}