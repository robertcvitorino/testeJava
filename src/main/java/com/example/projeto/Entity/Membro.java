package com.example.projeto.Entity;

import com.example.projeto.DTO.MembroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "MEMBRO")
@Entity(name = "MEMBRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Membro
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;
    @NotNull
    private String cargo;
    @NotNull
    private boolean funcionario;

    public Membro(MembroDTO membroDTO)
    {
        this.nome = membroDTO.nome();
        this.cargo = membroDTO.cargo();
        this.funcionario = membroDTO.funcionario();
    }

    public boolean getFuncionario(){
        return  this.funcionario;
    }
}
