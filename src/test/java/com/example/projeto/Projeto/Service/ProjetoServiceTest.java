package com.example.projeto.Projeto.Service;



import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Projeto.DTO.ProjetoDTO;
import com.example.projeto.Projeto.Entity.Projeto;
import com.example.projeto.Projeto.Enum.Classificacao;
import com.example.projeto.Projeto.Enum.StatusProjeto;
import com.example.projeto.Projeto.Repository.ProjetoRepository;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjetoServiceTest {
    @InjectMocks
    private ProjetoService projetoService;
    @Mock
    private ProjetoRepository projetoRepository;
    private Optional<Projeto> projetoOptional;
    private Projeto projeto;
    private ProjetoDTO projetoDTO;
    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        mockProjeto();
    }
    @Test
    void buscarProjeto()
    {
        when(projetoRepository.findById(Mockito.anyLong())).thenReturn(projetoOptional);

        Optional<Projeto> response = projetoService.buscarProjeto(1L);

        Assertions.assertEquals(projetoOptional,response);

    }

    @Test
    void buscarTodosProjeto()
    {

       when(projetoRepository.findAll()).thenReturn(List.of(projeto));

        List<Projeto> response = projetoService.buscarTodosProjeto();

        Assertions.assertEquals(1,response.size());

    }

    @Test
    void cadastrarProjeto()
    {
        when(projetoRepository.save(any())).thenReturn(projeto);

        Projeto response= projetoService.cadastrarProjeto(projeto);

        Assertions.assertEquals(projeto,response);
    }

    @Test
    void existeProjetoCadastrado()
    {
        when(projetoRepository.existsById(any())).thenReturn(true);

        boolean response = projetoService.existeProjetoCadastrado(2L);

        Assertions.assertEquals(true,response);

    }

    @Test
    void atualizarProjeto()
    {
        when(projetoRepository.save(any())).thenReturn(projeto);

        Projeto response= projetoService.atualizarProjeto(projeto);

        Assertions.assertEquals(projeto,response);
    }

    @Test
    void deletarProjeto()
    {
        doNothing().when(projetoRepository).deleteById(anyLong());
        projetoService.deletarProjeto(2L);
        verify(projetoRepository,times(1)).deleteById(anyLong());

    }

    public void mockProjeto()
    {
        List<Membro> membros = new ArrayList<Membro>();
        membros.add(new Membro(2L,"Jorge", "Programador",false));

        projetoDTO = new ProjetoDTO(
                Optional.of(1L),
                "Projeto de Sentença",
                LocalDate.parse("2016-06-01"),
                "Jorge",
                LocalDate.parse("2016-06-01"),
                LocalDate.parse("2016-06-01"),
                1000.0,
                "boa noite, severino",
                StatusProjeto.ENCERRADO,
                Classificacao.ALTO,
                membros

        );
        projetoOptional = Optional.of(new Projeto(1L,
            "Projeto de Sentença",
            LocalDate.parse("2016-06-01"),
            "Jorge",
            LocalDate.parse("2016-06-01"),
            LocalDate.parse("2016-06-01"),
            1000.0,
            "boa noite, severino",
            StatusProjeto.ENCERRADO,
            Classificacao.ALTO,
            membros
    ));
        projeto = new Projeto(1L,
                "Projeto de Sentença",
                LocalDate.parse("2016-06-01"),
                "Jorge",
                LocalDate.parse("2016-06-01"),
                LocalDate.parse("2016-06-01"),
                1000.0,
                "boa noite, severino",
                StatusProjeto.ENCERRADO,
                Classificacao.ALTO,
                membros
        );
    }
}