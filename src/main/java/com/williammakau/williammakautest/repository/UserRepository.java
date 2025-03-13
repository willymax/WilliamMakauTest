package com.williammakau.williammakautest.repository;

import com.williammakau.williammakautest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

} 