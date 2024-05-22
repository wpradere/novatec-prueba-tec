package dev.william.microsbankinkcredibanco.services;

import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import dev.william.microsbankinkcredibanco.models.repository.CreditCardRepository;
import dev.william.microsbankinkcredibanco.models.repository.TransactionRepository;
import dev.william.microsbankinkcredibanco.models.request.BlockCardRequest;
import dev.william.microsbankinkcredibanco.models.request.GenerateCardRequest;
import dev.william.microsbankinkcredibanco.models.response.BlockCardResponse;
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
public class BlockCardService implements IBlockCard {


    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public BlockCardResponse blockCard(BlockCardRequest request) {
        BlockCardResponse response = new BlockCardResponse();
        var cardBlock = creditCardRepository.findById(request.getProductId()).orElseThrow(()->new IdNotFoundExceptions("Card"));
        var transactionPersist = TransactionEntity.builder()
                .typeTransaction(TypeTransaction.Activar_Tarjeta)
                .descriptionTransaction("The card is delete succesfull !!! ")
                .stateTransaccion(CreditCardProcess.sucess)
                .valueTransaction(BigDecimal.ZERO)
                .createAt(LocalDateTime.now())
                .idCustomer(cardBlock.getCustomer().getDni())
                .idCreditCard(cardBlock.getProductId())
                .build();
                this.transactionRepository.save(transactionPersist);
                this.creditCardRepository.delete(cardBlock);
                response.setIdProduct(request.getProductId());
                response.setMesssage("The Card Was delete succesfull ");

        return response;
    }
}