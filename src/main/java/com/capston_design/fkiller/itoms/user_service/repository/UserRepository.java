package com.capston_design.fkiller.itoms.user_service.repository;

import com.capston_design.fkiller.itoms.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
