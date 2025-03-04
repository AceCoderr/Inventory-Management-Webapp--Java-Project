package com.webapp.invems.Model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends JpaRepository<StoreUser,Long> {

    Optional<StoreUser> findByUsername(String username);
}
