package dev.william.microsbankinkcredibanco.controller;


import dev.william.microsbankinkcredibanco.models.request.*;
import dev.william.microsbankinkcredibanco.models.response.*;
import dev.william.microsbankinkcredibanco.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@AllArgsConstructor
@RequestMapping("card")
public class CreditCardController {

    private final ICreateGenericCard creditcard;
    private final ICreditCardGenerate creditCardActivation;
    private final IActivateCard activateCard;
    private final IBlockCard blockCard;
    private final IChargeCardService chargeCardService;
    private final ICheckBlance checkBlance;
    private final ITransactionPuchase transactionPuchase;
    private final  IConsultTransaction consultTransaction;
    private final IAnulaTrans anulacionTrans;

    @GetMapping("/create")
    public ResponseEntity<GenerateCardResponse> insertar ( @Valid @RequestBody GenerateCardRequest request/*,@PathVariable Long productId*/){
        return ResponseEntity.ok(creditcard.crearTarjeta(request));
    }

    @GetMapping("/{productId}/number")
    public ResponseEntity<AssignedCardResponse> asignar ( @PathVariable Long productId){
        AssignedCardRequest request = new AssignedCardRequest();
        request.setProductId(productId);
        return ResponseEntity.ok(creditCardActivation.activar(request));
    }

    @PostMapping("/enroll")
    public ResponseEntity<ActivateCardResponse> actiCard (@RequestBody ActivateCardRequest request ){
        return ResponseEntity.ok(activateCard.activarCard(request));

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<BlockCardResponse> bloquear(@PathVariable Long productId){
        BlockCardRequest request = new BlockCardRequest();
        request.setProductId(productId);
        return ResponseEntity.ok(blockCard.blockCard(request));
    }

    @PostMapping("/balance")
    public ResponseEntity<ChargeCardResponse>cargarBalance( @RequestBody ChargeCardRequest request){
        return ResponseEntity.ok(chargeCardService.cargarTarjeta(request));
    }

    @GetMapping("/balance/{productId}")
    public ResponseEntity<CheckBalanceResponse>checkBalance(@PathVariable Long productId){
        CheckBalanceRequest request= new CheckBalanceRequest();
        request.setProductId(productId);
       return ResponseEntity.ok(checkBlance.chechCard(request));
    }

    @PostMapping("/transaction/puchase")
    public ResponseEntity<TransactionPuchaseResponse> activateC ( @RequestBody TransactionPuchaseRequest  request ){
    return ResponseEntity.ok(transactionPuchase.createTransaction(request));
    }
    @GetMapping("/transaction/{transactionid}")
    public ResponseEntity<ConsultTransactionResponse>checkTrnasaction( @PathVariable Long transactionid){
        ConsultTransactionRequest request= new ConsultTransactionRequest();
        request.setTransactionId(transactionid);
        return ResponseEntity.ok(consultTransaction.checkTrnsaction(request));
    }

    @PostMapping("/transaction/anulation")
    public ResponseEntity<AnulationTransactionResponse> anulacion (  @RequestBody AnulationTransactionRequest  request ){
        return ResponseEntity.ok(anulacionTrans.anulationTrnas(request));

    }
}
