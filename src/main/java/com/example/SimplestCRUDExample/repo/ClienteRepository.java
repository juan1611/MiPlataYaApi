package com.example.SimplestCRUDExample.repo;

import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
