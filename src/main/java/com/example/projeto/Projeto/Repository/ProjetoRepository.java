package com.example.projeto.Projeto.Repository;

import com.example.projeto.Projeto.Entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository  extends JpaRepository<Projeto,Long> {
}
