package com.ll.sbb.user.repository;


import com.ll.sbb.user.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
}
