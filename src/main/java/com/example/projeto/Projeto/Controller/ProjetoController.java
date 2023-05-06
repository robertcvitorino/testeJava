package com.example.projeto.Projeto.Controller;

import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Membro.Service.MembroService;
import com.example.projeto.Projeto.Enum.StatusProjeto;
import com.example.projeto.Projeto.DTO.ProjetoDTO;
import com.example.projeto.Projeto.Entity.Projeto;
import com.example.projeto.Projeto.Service.ProjetoService;
import com.example.projeto.Utils.ResponseError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private MembroService membroService;

    @GetMapping("/{id}")
    public ResponseEntity buscarProjeto(@PathVariable("id") Long id)
    {
        if(this.projetoService.existeProjetoCadastrado(id))
        {
           return ResponseEntity.ok(this.projetoService.buscarProjeto(id));
        }

        return ResponseError.retornoComErro("Projeto não cadastrado");
    }
    @GetMapping
    public ResponseEntity buscarTodosProjetosCastrados()
    {
        List<ProjetoDTO> projetoDTO = this.projetoService.buscarTodosProjeto().stream().map(ProjetoDTO::new).toList();

        if(!projetoDTO.isEmpty())
        {
          return ResponseEntity.ok(projetoDTO);
        }

        return ResponseError.retornoComErro("Não há projetos cadastrados ");
    }

    @PostMapping
    public ResponseEntity cadastrarProjeto(@RequestBody ProjetoDTO projetoDTO)
    {
        Projeto projeto = new Projeto(projetoDTO);

        if(projeto != null && validarMembros(projetoDTO.membros()))
        {
            return ResponseEntity.ok(this.projetoService.cadastrarProjeto(projeto));
        }

        return ResponseError.retornoComErro("Erro ao cadastrar projeto ");
    }

    @PutMapping
    public ResponseEntity atualizarProjeto(@RequestBody ProjetoDTO projetoDTO)
    {
        if(this.projetoService.existeProjetoCadastrado(projetoDTO.id().isPresent() ? projetoDTO.id().get() : null) &&  validarMembros(projetoDTO.membros()))
        {
            Projeto projeto = new Projeto(projetoDTO);
            projeto.setId( projetoDTO.id().get());

            return ResponseEntity.ok(this.projetoService.atualizarProjeto(projeto));
        }

        return ResponseError.retornoComErro("Ocorreu erro ao atualizar projeto");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProjeto(@PathVariable("id") Long id)
    {
        Optional<Projeto> projeto = this.projetoService.buscarProjeto(id);

        if(projeto == null || !validarProjetoParaDeletar(projeto))
        {
            return ResponseError.retornoComErro("Projeto não pode ser deletado");
        }
            this.projetoService.existeProjetoCadastrado(id);
            return ResponseEntity.ok("Projeto deletado");
    }

    private boolean validarProjetoParaDeletar(Optional<Projeto> projeto)
    {
        return (projeto.get().getStatus().equals(StatusProjeto.EM_ANDAMENTO) ||
                projeto.get().getStatus().equals(StatusProjeto.INICIADO) ||
                projeto.get().getStatus().equals(StatusProjeto.ENCERRADO));
    }

    private boolean validarMembros(List<Membro> membros)
    {
        for (Membro membro : membros)
        {
          if(!this.membroService.existeMembro(membro.getId()))
          {
              return false;
          }
        }

        return true;
    }
}

