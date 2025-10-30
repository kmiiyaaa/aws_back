package com.kmii.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmii.home.entity.User;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);

}
