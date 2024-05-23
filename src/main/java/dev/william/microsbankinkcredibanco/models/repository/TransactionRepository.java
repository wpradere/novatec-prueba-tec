package dev.william.microsbankinkcredibanco.models.repository;

import dev.william.microsbankinkcredibanco.entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

}
