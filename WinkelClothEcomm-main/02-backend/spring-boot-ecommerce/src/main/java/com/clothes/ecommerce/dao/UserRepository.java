package com.clothes.ecommerce.dao;
import com.clothes.ecommerce.entity.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<LocalUser, Long> {
}
