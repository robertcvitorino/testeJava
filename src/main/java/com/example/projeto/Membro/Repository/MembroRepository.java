package com.example.projeto.Membro.Repository;

import com.example.projeto.Membro.Entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro,Long> {
}
