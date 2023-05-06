package com.example.projeto.Projeto.DTO;

import com.example.projeto.Projeto.Enum.Classificacao;
import com.example.projeto.Projeto.Enum.StatusProjeto;
import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Projeto.Entity.Projeto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record ProjetoDTO(
         Optional<Long> id,
         @NotBlank
         String nome,
         @NotBlank
         LocalDate dataInicio,
         @NotBlank
         String gerenteResponsavel,
         @NotBlank
         LocalDate previsaoTermino,
         @NotBlank
         LocalDate dataRealTermino,
         @NotBlank
         Double orcamentoTotal,
         String descricao,
         StatusProjeto status,
         Classificacao classificacao,
         List<Membro> membros
) {
    public ProjetoDTO(Projeto projeto){
        this(
                Optional.of(projeto.getId()),
                projeto.getNome(),
                projeto.getDataInicio(),
                projeto.getGerenteResponsavel(),
                projeto.getPrevisaoTermino(),
                projeto.getDataRealTermino(),
                projeto.getOrcamentoTotal(),
                projeto.getDescricao(),
                projeto.getStatus(),
                projeto.getClassificacao(),
                projeto.getMembros());
    }

}
