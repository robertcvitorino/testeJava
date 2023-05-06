package com.example.projeto.Service;


import com.example.projeto.Entity.Membro;
import com.example.projeto.Repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {
    @Autowired
    private MembroRepository membroRepository;

    public Optional<Membro> buscarMembro(Long id)
    {
        return this.membroRepository.findById(id);
    }
    public List<Membro> buscarTodosMembro()
    {
        return this.membroRepository.findAll();
    }

    public void cadastrarMembro(Membro membro)
    {
        this.membroRepository.save(membro);
    }
    public void atualizarMembro(Membro membro)
    {
        this.membroRepository.save(membro);
    }
    public void deletarMembro(Long id)
    {
       this.membroRepository.deleteById(id);
    }
    public boolean existeMembro(Long id)
    {
        return this.membroRepository.existsById(id);
    }
}
