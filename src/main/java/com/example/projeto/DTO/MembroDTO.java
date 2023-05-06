package com.example.projeto.DTO;
import com.example.projeto.Entity.Membro;
import jakarta.validation.constraints.*;

import java.util.Optional;

public record MembroDTO(Optional<Long>  id, @NotNull String nome , @NotNull String cargo,@NotNull boolean funcionario) {
    public MembroDTO(Membro membro){
        this(Optional.of(membro.getId()),membro.getNome(), membro.getCargo(),membro.getFuncionario());
    }
}
