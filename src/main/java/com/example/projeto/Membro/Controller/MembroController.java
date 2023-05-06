package com.example.projeto.Membro.Controller;

import com.example.projeto.Membro.DTO.MembroDTO;
import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Membro.Service.MembroService;
import com.example.projeto.Utils.ResponseError;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("membro")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping("/{id}")
    public ResponseEntity buscarMembro(@PathVariable("id") Long id)
    {

        if(this.membroService.existeMembro(id))
        {
            return ResponseEntity.ok(this.membroService.buscarMembro(id));
        }

        return ResponseError.retornoComErro("Ocorreu erro ao buscar membro");

    }
    @GetMapping()
    public ResponseEntity buscarTodosMembros()
    {
        List<MembroDTO> membroDTO = this.membroService.buscarTodosMembro().stream().map(MembroDTO::new).toList();

        if(membroDTO !=null)
        {
            return ResponseEntity.ok(membroDTO);
        }

        return ResponseError.retornoComErro("Ocorreu erro ao buscar todos os membro");
    }

    @PostMapping
    public ResponseEntity castrarMembros(@RequestBody @Valid MembroDTO membroDTO)
    {
        Membro membro = new Membro(membroDTO);

        if(membro !=null)
        {
            return ResponseEntity.ok(this.membroService.cadastrarMembro(membro));
        }

        return ResponseError.retornoComErro("Membro cadastrado com sucesso");

    }
    @PutMapping
    public ResponseEntity atualizarMembro(@RequestBody @Valid MembroDTO membroDTO)
    {
        Membro membro = new Membro(membroDTO);

        if(this.membroService.existeMembro(membroDTO.id().isPresent() ? membroDTO.id().get() : null))
        {
            membro.setId(membroDTO.id().isPresent() ? membroDTO.id().get() : null);
            return ResponseEntity.ok(this.membroService.atualizarMembro(membro));
        }

        return ResponseError.retornoComErro("Ocorreu erro ao atualizar membro");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMembro(@PathVariable("id") Long id)
    {

        if(this.membroService.existeMembro(id))
        {
            this.membroService.deletarMembro(id);
            return ResponseEntity.ok("Membro deletado com sucesso");
        }

        return ResponseError.retornoComErro("Ocorreu erro ao deletar membro");

    }

}
