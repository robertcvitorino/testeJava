package com.example.projeto.Controller;

import com.example.projeto.DTO.MembroDTO;
import com.example.projeto.Entity.Membro;
import com.example.projeto.Service.MembroService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

        return retornoComErro("Ocorreu erro ao buscar membro");

    }
    @GetMapping()
    public ResponseEntity buscarTodosMembro()
    {
        List<MembroDTO> membroDTO = this.membroService.buscarTodosMembro().stream().map(MembroDTO::new).toList();

        if(membroDTO !=null)
        {
            return ResponseEntity.ok(membroDTO);
        }

        return retornoComErro("Ocorreu erro ao buscar todos os membro");
    }

    @PostMapping
    public ResponseEntity castrarMembros(@RequestBody MembroDTO membroDTO)
    {
        Membro membro = new Membro(membroDTO);

        if(membro !=null)
        {
            this.membroService.cadastrarMembro(membro);
            return ResponseEntity.ok(membro);
        }

        return retornoComErro("Membro cadastrado com sucesso");

    }
    @PutMapping
    public ResponseEntity atualizarMembro(@RequestBody MembroDTO membroDTO)
    {
        Membro membro = new Membro(membroDTO);

        if(this.membroService.existeMembro(membroDTO.id().isPresent() ? membroDTO.id().get() : null))
        {
            membro.setId(membroDTO.id().isPresent() ? membroDTO.id().get() : null);
            this.membroService.atualizarMembro(membro);
            return ResponseEntity.ok(membro);
        }

        return retornoComErro("Ocorreu erro ao atualizar membro");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarFuncionario(@PathVariable("id") Long id)
    {

        if(this.membroService.existeMembro(id))
        {
            this.membroService.deletarMembro(id);
            return ResponseEntity.ok("");
        }

        return retornoComErro("Ocorreu erro ao deletar membro");

    }

    private ResponseEntity retornoComErro(String mensagem)
    {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Gson().toJson(mensagem));
    }


}
