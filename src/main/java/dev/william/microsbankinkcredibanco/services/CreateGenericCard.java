package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.CustomerRepository;
import dev.william.microsbankinkcredibanco.models.request.GenerateCardRequest;
import dev.william.microsbankinkcredibanco.models.response.GenerateCardResponse;
import dev.william.microsbankinkcredibanco.util.exceptions.IdNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class CreateGenericCard implements ICreateGenericCard{

    private final CreditCardRepository creditCardRepository;
    private final CustomerRepository customerRepository;
    @Override
    public GenerateCardResponse crearTarjeta(GenerateCardRequest request) {

        GenerateCardResponse generaResposeCard = new GenerateCardResponse();
        var customerbd = customerRepository.findById(request.getCustomerid()).orElseThrow(()->new IdNotFoundExceptions("Customer") );
        var creditCard = CreditCardEntity.builder()
                .productId(request.getProductId())
                .typeCreditCard(request.getTypeCreditCard())
                .active(false)
                .expiry_date("")
                .balance(BigDecimal.ZERO)
                .createAt(LocalDate.now())
                .customer(customerbd)
                .build();

        var creditCardSave= this.creditCardRepository.save(creditCard);
        generaResposeCard.setMessage("Card was generate");
        return generaResposeCard;
    }
}
