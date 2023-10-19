package com.sbbank.accounts.repository;

import com.sbbank.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
   Optional<Accounts> findByCustomerId(Long customerId);
}
