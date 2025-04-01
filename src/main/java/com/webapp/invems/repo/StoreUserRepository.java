package com.webapp.invems.repo;

import java.util.Optional;

import com.webapp.invems.Model.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends JpaRepository<StoreUser,Long> {

    Optional<StoreUser> findByUsername(String username);
}
