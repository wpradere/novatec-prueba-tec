package dev.william.microsbankinkcredibanco.entities;


import dev.william.microsbankinkcredibanco.util.TypeCard;




import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Entity(name ="creditcard" )
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreditCardEntity {

    @Id
    private Long productId;
    private String idCardActivation;

    @Enumerated(EnumType.STRING)
    private TypeCard typeCreditCard;

    @Column(name = "enable")
        private boolean active;

    private String expiry_date;

    @Column(name = "quota_creditcard")
    private BigDecimal balance;

    @Column(name = "fecha_creacion")
    private LocalDate createAt;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card_customer")
    private CustomerEntity customer;



}
