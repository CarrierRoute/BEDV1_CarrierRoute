package com.grepp.carrierroute.user.repository;

import com.grepp.carrierroute.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
