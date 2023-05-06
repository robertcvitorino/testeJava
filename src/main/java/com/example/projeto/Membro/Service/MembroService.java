package com.example.projeto.Membro.Service;


import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Membro.Repository.MembroRepository;
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

    public Membro cadastrarMembro(Membro membro)
    {
        return  this.membroRepository.save(membro);
    }
    public Membro atualizarMembro(Membro membro)
    {
       return this.membroRepository.save(membro);
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
