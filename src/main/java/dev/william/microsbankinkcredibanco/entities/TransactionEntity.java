package dev.william.microsbankinkcredibanco.entities;

import dev.william.microsbankinkcredibanco.util.CreditCardProcess;
import dev.william.microsbankinkcredibanco.util.TypeTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @Column(name = "description_transaction")
    private String descriptionTransaction;

    @Column(name = "state_transaccion")
    @Enumerated(EnumType.STRING)
    private CreditCardProcess stateTransaccion;

    @Column(name = "value_transaction")
    private BigDecimal valueTransaction;

    @Column(name = "fecha_creacion")
    private LocalDateTime createAt;

    @Column(name = "id_customer")
    private String idCustomer;

    @Column(name = "id_credit_card")
    private Long idCreditCard;

}
