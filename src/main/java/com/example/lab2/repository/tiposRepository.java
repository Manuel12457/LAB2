package com.example.lab2.repository;

import com.example.lab2.Entity.Tipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface tiposRepository extends JpaRepository<Tipos,Integer> {
}
