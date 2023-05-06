package com.example.projeto.Projeto.Entity;

import com.example.projeto.Projeto.Enum.Classificacao;
import com.example.projeto.Projeto.Enum.StatusProjeto;
import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Projeto.DTO.ProjetoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "PROJETO")
@Entity(name = "PROJETO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private String gerenteResponsavel;
    private LocalDate previsaoTermino;
    private LocalDate dataRealTermino;
    private Double orcamentoTotal;
    private String descricao;
    @Enumerated(EnumType.ORDINAL)
    private StatusProjeto status;
    @Enumerated(EnumType.ORDINAL)
    private Classificacao classificacao;
    @ManyToMany
    private List<Membro> membros;
    public Projeto(ProjetoDTO projetoDTO)
    {
                this.nome = projetoDTO.nome();
                this.dataInicio = projetoDTO.dataInicio();
                this.gerenteResponsavel = projetoDTO.gerenteResponsavel();
                this.previsaoTermino = projetoDTO.previsaoTermino();
                this.dataRealTermino = projetoDTO.dataRealTermino();
                this.orcamentoTotal = projetoDTO.orcamentoTotal();
                this.descricao = projetoDTO.descricao();
                this.status = projetoDTO.status();
                this.classificacao =projetoDTO.classificacao();
                this.membros = projetoDTO.membros();


    }
}
