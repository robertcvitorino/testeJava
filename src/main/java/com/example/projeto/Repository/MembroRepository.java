package com.example.projeto.Repository;

import com.example.projeto.Entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro,Long> {
}
