package dev.william.microsbankinkcredibanco.models.repository;

import dev.william.microsbankinkcredibanco.entities.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity,Long> {
    Optional<CreditCardEntity>  findByIdCardActivation(String idCardActivation );
}
