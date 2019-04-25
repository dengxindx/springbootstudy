package com.dx.springbootmutildatasourcedatajpa.domain.p;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}