package dev.william.microsbankinkcredibanco.models.repository;

import dev.william.microsbankinkcredibanco.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,String> {

    Optional<CustomerEntity> findByDni(String dni);
}
