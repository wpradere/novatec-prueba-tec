package dev.william.microsbankinkcredibanco.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerEntity {

    @Id
    private String dni;
    private String name;
    private String lastname;
    private String phoneNumber;
    private Integer totalCreditCard;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    private Set<CreditCardEntity> creditCard;






}
