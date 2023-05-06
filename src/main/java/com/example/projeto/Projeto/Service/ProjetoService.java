package com.example.projeto.Projeto.Service;

import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Projeto.Entity.Projeto;
import com.example.projeto.Projeto.Repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    public Optional<Projeto> buscarProjeto(Long id)
    {
        return this.projetoRepository.findById(id);
    }
    public List<Projeto> buscarTodosProjeto()
    {
        return  this.projetoRepository.findAll();
    }

    public Projeto cadastrarProjeto(Projeto projeto)
    {
        return this.projetoRepository.save(projeto);
    }

    public  boolean existeProjetoCadastrado(Long id)
    {
        return this.projetoRepository.existsById(id);
    }

    public Projeto atualizarProjeto(Projeto projeto)
    {
       return this.projetoRepository.save(projeto);
    }
    public void deletarProjeto(Long id)
    {
        this.projetoRepository.deleteById(id);
    }
}
