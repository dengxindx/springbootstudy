package com.dx.springbootmutildatasourcedatajpa.domain.s;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}