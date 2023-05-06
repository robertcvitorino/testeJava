package com.example.projeto.Membro.Service;

import com.example.projeto.Membro.DTO.MembroDTO;
import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Membro.Repository.MembroRepository;
import com.example.projeto.Projeto.DTO.ProjetoDTO;
import com.example.projeto.Projeto.Entity.Projeto;
import com.example.projeto.Projeto.Enum.Classificacao;
import com.example.projeto.Projeto.Enum.StatusProjeto;
import com.example.projeto.Projeto.Repository.ProjetoRepository;
import com.example.projeto.Projeto.Service.ProjetoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class MembroServiceTest {

    @InjectMocks
    private MembroService membroService;
    @Mock
    private MembroRepository membroRepository;
    private Optional<Membro> membroOptional;
    private Membro membro;
    private MembroDTO membroDTO;
    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        mockMembro();
    }
    @Test
    void buscarMembro()
    {
        when(membroRepository.findById(Mockito.anyLong())).thenReturn(membroOptional);

        Optional<Membro> response = membroService.buscarMembro(1L);

        Assertions.assertEquals(membroOptional,response);
    }

    @Test
    void buscarTodosMembro() {
        when(membroRepository.findAll()).thenReturn(List.of(membro));

        List<Membro> response = membroService.buscarTodosMembro();

        Assertions.assertEquals(1,response.size());
    }

    @Test
    void cadastrarMembro()
    {
        when(membroRepository.save(any())).thenReturn(membro);

        Membro response= membroService.cadastrarMembro(membro);

        Assertions.assertEquals(membro,response);
    }

    @Test
    void atualizarMembro()
    {
        when(membroRepository.save(any())).thenReturn(membro);

        Membro response= membroService.atualizarMembro(membro);

        Assertions.assertEquals(membro,response);
    }

    @Test
    void deletarMembro()
    {
        doNothing().when(membroRepository).deleteById(anyLong());
        membroService.deletarMembro(2L);
        verify(membroRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void existeMembro()
    {
        when(membroRepository.existsById(any())).thenReturn(true);

        boolean response = membroService.existeMembro(2L);

        Assertions.assertEquals(true,response);
    }
    public void mockMembro()
    {
        membroDTO = new MembroDTO(Optional.of(1L),"Jorge","QA",false);
        membroOptional = Optional.of( new Membro(1L,"Jorge","QA",false));
        membro = new Membro(1L,"Jorge","QA",false);
    }
}