package edu.pnu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanUser;

@Repository
public interface BusanUserRepository extends JpaRepository<BusanUser, Long> {
    Optional<BusanUser> findByUsername(String username);
}
