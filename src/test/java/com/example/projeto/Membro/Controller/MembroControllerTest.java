package com.example.projeto.Membro.Controller;

import com.example.projeto.Membro.DTO.MembroDTO;
import com.example.projeto.Membro.Entity.Membro;
import com.example.projeto.Membro.Repository.MembroRepository;
import com.example.projeto.Membro.Service.MembroService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.jboss.logging.NDC.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MembroControllerTest {
    @Autowired
    private MembroController membroController;
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
        MockitoAnnotations.openMocks(this.membroController);
        mockMembro();
    }
    @Test
    void buscarMembro() throws Exception {
        when(membroRepository.findById(Mockito.anyLong())).thenReturn(membroOptional);
        when(membroService.buscarMembro(Mockito.anyLong())).thenReturn(membroOptional);
        ResponseEntity response = membroController.buscarMembro(2L);

        assertEquals(true,response.hasBody());
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void buscarTodosMembros()
    {
        when(membroRepository.findById(Mockito.anyLong())).thenReturn(membroOptional);
        when(membroService.buscarMembro(Mockito.anyLong())).thenReturn(membroOptional);
        ResponseEntity response = membroController.buscarTodosMembros();

        assertEquals(true,response.hasBody());
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void castrarMembros()
    {
        when(membroRepository.save(Mockito.any())).thenReturn(membro);
        when(membroService.cadastrarMembro(membro)).thenReturn(membro);
        ResponseEntity response = membroController.castrarMembros(membroDTO);

        assertEquals(true,response.hasBody());
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void atualizarMembro() {
    }

    @Test
    void deletarMembro() {
    }
    public void mockMembro()
    {
        membroDTO = new MembroDTO(Optional.of(1L),"Jorge","QA",false);
        membroOptional = Optional.of( new Membro(1L,"Jorge","QA",false));
        membro = new Membro(1L,"Jorge","QA",false);
    }
}